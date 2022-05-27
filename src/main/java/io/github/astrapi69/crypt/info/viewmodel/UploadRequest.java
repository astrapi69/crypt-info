package io.github.astrapi69.crypt.info.viewmodel;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UploadRequest
{
	MultipartFile multipartFile;
	String filepath;
}
