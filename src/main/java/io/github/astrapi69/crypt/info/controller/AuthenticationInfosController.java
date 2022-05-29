/**
 * The MIT License
 *
 * Copyright (C) 2020 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.crypt.info.controller;

import io.github.astrapi69.crypt.info.jpa.entity.AuthenticationInfos;
import io.github.astrapi69.crypt.info.mapper.AuthenticationInfosMapper;
import io.github.astrapi69.crypt.info.service.AuthenticationInfosService;
import io.github.astrapi69.crypt.info.viewmodel.AuthenticationInfo;
import io.github.astrapi69.crypt.info.enumtype.AppRestPath;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppRestPath.REST_VERSION + AppRestPath.REST_AUTHENTICATION_INFOS)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationInfosController
{
	AuthenticationInfosService service;
	AuthenticationInfosMapper mapper;

	@RequestMapping(method = RequestMethod.POST)
	public AuthenticationInfo newTemplate()
	{
		AuthenticationInfos entity = service
			.save(AuthenticationInfos.builder().password("secret").build());
		return mapper.toDto(entity);
	}
}
