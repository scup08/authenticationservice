/**
 * @author 51937
 * @date 2021年4月21日
 * @version V1.0
 */
package com.lzh.authenticationservice.config;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.NoSuchClientException;

/**
 * @author 51937
 * @date 2021年4月21日
 */
public class MyClientDetailsService implements ClientDetailsService, ClientRegistrationService {

	@Override
	public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeClientDetails(String clientId) throws NoSuchClientException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ClientDetails> listClientDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
		// TODO  自己实现客户端存储就行了
		ClientDetails details;
//		try {
//			details = jdbcTemplate.queryForObject(selectClientDetailsSql, new ClientDetailsRowMapper(), clientId);
//		}
//		catch (EmptyResultDataAccessException e) {
//			throw new NoSuchClientException("No client with requested id: " + clientId);
//		}

		return null;
	}

}
