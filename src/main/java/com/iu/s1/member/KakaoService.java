package com.iu.s1.member;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
@Service
public class KakaoService extends DefaultOAuth2UserService{

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User auth2User= super.loadUser(userRequest);
		Map<String, Object> map = (Map<String, Object>)auth2User.getAttributes();
		Map<String, Object> hashMap=(Map<String, Object>)map.get("kakao_account");
		System.out.println(hashMap.get("email").toString());
		return this.join(userRequest);
	}
	
	
	private OAuth2User join(OAuth2UserRequest userRequest)throws OAuth2AuthenticationException{
		// TODO Auto-generated method stub
				System.out.println("Social 로그인 결과 객체");
				OAuth2User auth2User= super.loadUser(userRequest);
				
				System.out.println("Social Login 제공 : kakao, google 등");
				System.out.println("Provider : "+userRequest.getClientRegistration().getClientName());

				System.out.println("Login 결과에서 User 정보 조회");
				Map<String, Object> map = (Map<String, Object>)auth2User.getAttributes();
				
				Iterator<String> it = map.keySet().iterator();
				
				while(it.hasNext()) {
					String key = it.next();
					System.out.println("Key :"+ key );
					/**
					  	Key :id				value : 1129858552 -고유 아이디
						Key :connected_at	value : 2019-07-22T03:05:22Z 
						Key :properties		value : {nickname=김대기}
						Key :kakao_account	value : {profile_needs_agreement=false, profile={nickname=김대기, thumbnail_image_url=http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_110x110.jpg, profile_image_url=http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg, is_default_image=true}, has_email=true, email_needs_agreement=false, is_email_valid=true, is_email_verified=true, email=kimdaeki@gmail.com}
					 */
					System.out.println("Value : "+ map.get(key));
					
				}
				
				System.out.println("SocialVO를 생성해서 값 적용");
				KakaoVO kakaoVO = new KakaoVO();
				
				
				System.out.println(map.get("properties").getClass());
				// nickname을 조회해 username으로 적용
				//java.util.LinkedHashMap
				Map<String, Object> hashMap=(Map<String, Object>)map.get("properties");
				
				kakaoVO.setUsername(hashMap.get("nickname").toString());
				
				// password가 없으므로 nickname으로 대체 또는 랜덤 생성
				kakaoVO.setPassword(UUID.randomUUID().toString());
				
				// name도 nickname으로 대체 
				kakaoVO.setName(hashMap.get("nickname").toString());
				
				//이메일 조회
				hashMap=(Map<String, Object>)map.get("kakao_account");
				System.out.println(hashMap.get("email").toString());
				kakaoVO.setEmail(hashMap.get("email").toString());
				
				//사진 이미지 등 조회
				hashMap=(Map<String, Object>)hashMap.get("profile");
				//kakaoVO.setEmail(null);
				it = hashMap.keySet().iterator();
				
				while(it.hasNext()) {
					String key = it.next();
					System.out.println("Key :"+ key );
					/**
						Key :nickname
						Value : 김대기
						Key :thumbnail_image_url
						Value : http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_110x110.jpg
						Key :profile_image_url
						Value : http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg
						Key :is_default_image
						Value : true
					 */
					System.out.println("Value : "+ hashMap.get(key));
					
				}
				
				//
				kakaoVO.setSocial(userRequest.getClientRegistration().getClientName());
				
				kakaoVO.setAttributes(auth2User.getAttributes());
				
		return kakaoVO;
	}
	
}
