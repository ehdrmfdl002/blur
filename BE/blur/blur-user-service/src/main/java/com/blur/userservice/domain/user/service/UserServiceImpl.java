package com.blur.userservice.domain.user.service;

import com.blur.userservice.domain.user.dto.UserDto;
import com.blur.userservice.domain.user.entity.User;
import com.blur.userservice.domain.user.exception.DuplicateUserEmail;
import com.blur.userservice.domain.user.exception.NotExistUserException;
import com.blur.userservice.domain.user.repository.UserRepository;
import com.blur.userservice.global.client.store.StoreClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.annotation.AnnotationList.Empty;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

//    private final CustomerRepository customerRepository;
//    private final StoreOwnerRepository storeOwnerRepository;
    private final UserRepository userRepository;
//    private final StoreClient storeClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found in the database"));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(user.getDtype()));
        return new org.springframework.security.core.userdetails.User(user.getUserNo().toString(), user.getPassword(), authorities);
    }

    @Override
    public UserDto findUserByUserId(Integer userId) {
    	User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotExistUserException("존재하지 않는 사용자 입니다."));

        return new UserDto(user);
    }

    @Override
    public List<UserDto> findUserDtoByUserIds(List<Integer> userIds) {
        return userRepository.findAllById(userIds)
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

//    @Override
//    public UserDto findOwnerById(Long userId) {
//        StoreOwner storeOwner = storeOwnerRepository.findById(userId)
//                .orElseThrow(() -> new NotExistUserException(userId + "는 존재하지 않은 사용자입니다."));
//
//        return StoreOwnerDto.of(storeOwner);
//    }

    @Transactional
    @Override
    public void saveUser(UserDto userDto) {

//        StoreOwner storeOwner = postOwnerDto.toStoreOwner();
        
        String email = userDto.getEmail();
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateUserEmail(email + "은 중복된 이메일 입니다.");
        }

//        StoreOwner savedOwner = storeOwnerRepository.save(storeOwner);

        userRepository.save(userDto);
   
//        Long userId = savedOwner.getId();
//        storeClient.postStore(postStoreDto.toPostStoreRequest(), userId);
    }
    
//    public User register(UserDto dto) {
//        User user = dto.toEntity();
//        user.updatePassword(encoder.encode(dto.getPassword()));
//        userRepository.save(user);
//        UserProfileDto userProfileDto = new UserProfileDto();
//        UserProfile userProfile = userProfileDto.toEntity(user);
//        userProfile.setUser(user);
//        userProfileRepository.save(userProfile);
//        System.out.println("DB에 회원 저장 성공");
//        return user;
//
//    }
}
