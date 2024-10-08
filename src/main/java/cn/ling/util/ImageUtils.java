package cn.ling.util;

import cn.ling.constant.CommonConstants;
import cn.ling.exception.BadRequestException;
import cn.ling.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

/**
 * 图片下载保存工具类
 */
@Component
public class ImageUtils {
	private ImageUtils(){}

	private static final RestTemplate REST_TEMPLATE = new RestTemplate();

	@AllArgsConstructor
	@Getter
	static class ImageResource {
		byte[] data;
		//图片拓展名 jpg png
		String type;
	}

	public static ImageResource getImageByRequest(String url) {
		ResponseEntity<byte[]> responseEntity = REST_TEMPLATE.getForEntity(url, byte[].class);
		MediaType contentType = responseEntity.getHeaders().getContentType();
		if(contentType == null){
			throw new NotFoundException("getImageByRequest is null---ImageUtils.class");
		}
		if (CommonConstants.IMAGE.equals(contentType.getType())) {
			return new ImageResource(responseEntity.getBody(), contentType.getSubtype());
		}
		throw new BadRequestException("response contentType unlike image");
	}

	public static String pushGithub(ImageResource image, String githubToken, String githubUsername, String githubRepos, String githubReposPath) {
		String fileName = UUID.randomUUID() + "." + image.getType();
		String url = String.format(CommonConstants.GITHUB_UPLOAD_API, githubUsername, githubRepos, githubReposPath, fileName);
		String imgBase64 = Base64.getEncoder().encodeToString(image.getData());

		HttpHeaders headers = new HttpHeaders();
		headers.put("Authorization", Collections.singletonList("token " + githubToken));

		HashMap<String, String> body = new HashMap<>(16);
		body.put("message", "Add files via RBlog");
		body.put("content", imgBase64);
		HttpEntity<HashMap<String, String>> httpEntity = new HttpEntity<>(body, headers);
		REST_TEMPLATE.put(url, httpEntity);

		return String.format(CommonConstants.CDN_URL4_GITHUB, githubUsername, githubRepos, githubReposPath, fileName);
	}
}
