package com.example.userservice.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.command.UserCommand;
import com.example.userservice.command.queryCommand.UserQueryCommand;
import com.example.userservice.entity.UserEntity;
import com.example.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Value("${UPLOAD_FOLDER_PREFIX}")
    String UPLOAD_FOLDER_PREFIX;


    @Override
    public UserCommand getUserByUsername(String userName) {
        log.info("query username:{}", userName);
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", userName);
        UserEntity userEntity = userMapper.selectOne(wrapper);
        if (Objects.isNull(userEntity)) {
            return null;
        }
        UserCommand command = new UserCommand();
        BeanUtils.copyProperties(userEntity, command);
        command.setUserId(userEntity.getId());
        log.info("query result:{}", command);
        return command;
    }

    @Override
    public UserCommand getUserById(Integer userId) {
        log.info("query userId:{}", userId);
        UserEntity userEntity = userMapper.selectById(userId);
        UserCommand command = new UserCommand();
        command.setUserId(userEntity.getId());
        command.setUsername(userEntity.getUserName());
        BeanUtils.copyProperties(userEntity, command);
        log.info("query result:{}", command);
        return command;
    }

    @Override
    public int nameCheck(String userName) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", userName);
        UserEntity userEntity = userMapper.selectOne(wrapper);
        if (Objects.isNull(userEntity)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int update(UserCommand user) {
        try {
            UserEntity userEntity = userMapper.selectById(user.getUserId());
            BeanUtils.copyProperties(user, userEntity);
            userMapper.updateById(userEntity);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public List<UserCommand> getList(UserQueryCommand query) {
        Page<UserEntity> page = new Page<>(query.getPageNum(), query.getPageSize());
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name", query.getUserName()).or().like("user_nickname", query.getUserNickname());
        List<UserEntity> listEntity = userMapper.selectList(page, queryWrapper);
        List<UserCommand> listResult = new ArrayList<>();
        for (UserEntity tmp : listEntity) {
            UserCommand tmpCommand = new UserCommand();
            BeanUtils.copyProperties(tmp, tmpCommand);
            tmpCommand.setUserId(tmp.getId());
            listResult.add(tmpCommand);
        }
        return listResult;
    }

    @Override
    public ResponseEntity imageUpload(MultipartFile file, int id) {
        if (file != null && !file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();
                String uniqueFileName = generateUniqueFileName(originalFilename);

                // 创建目标文件路径
                Path targetPath = Paths.get(UPLOAD_FOLDER_PREFIX, uniqueFileName);

                // 获取上传文件的输入流
                InputStream inputStream = file.getInputStream();

                // 将文件内容从输入流拷贝到目标文件
                Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);

                UserEntity userEntity = userMapper.selectById(id);

                //delete original file
                Files.delete(Paths.get(userEntity.getUserPicture()));
                userEntity.setId(id);
                userEntity.setUserPicture(UPLOAD_FOLDER_PREFIX +'/'+ uniqueFileName);
                userMapper.updateById(userEntity);

                return ResponseEntity.ok("文件上传成功：" + uniqueFileName);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body("文件上传失败: " + e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("未选择文件");
        }
    }
    private String generateUniqueFileName(String originalFilename) {
        String uniqueName = UUID.randomUUID().toString();
        return uniqueName + "_" + originalFilename;
    }
}
