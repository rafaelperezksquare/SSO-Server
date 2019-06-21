package com.ksquare.sso.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.ksquare.sso.domain.OauthClientDetails;
import com.ksquare.sso.domain.OauthClientDetailsDTO;
import com.ksquare.sso.repository.OauthClientDetailsRepository;

@Service("oauthClientDetailsService")
@Transactional
public class OauthClientDetailsServiceImpl implements OauthClientDetailsService {
	
	private static final int PASS_SIZE = 100;
	
	@Autowired
	private OauthClientDetailsRepository oauthClientDetailsRepository;
	
	private RandomStringUtils randomStringUtils;
	
	public OauthClientDetailsServiceImpl() {
		randomStringUtils = new RandomStringUtils();
	}

	@Override
	public List<OauthClientDetailsDTO> getAPIclients() {
		ArrayList<OauthClientDetails> clients = Lists.newArrayList(oauthClientDetailsRepository.findAll()); 
		ArrayList<OauthClientDetailsDTO> clientsDTO = new ArrayList<OauthClientDetailsDTO>();
		for(OauthClientDetails client : clients) {
			OauthClientDetailsDTO clientDTO = new OauthClientDetailsDTO(client);
			clientsDTO.add(clientDTO);
		}
		return clientsDTO;
	}

	@Override
	public OauthClientDetailsDTO getAPIclient(String id) {
		OauthClientDetails client = oauthClientDetailsRepository.findOne(id);
		OauthClientDetailsDTO clientDTO = new OauthClientDetailsDTO(client); 
		return clientDTO;
	}

	@SuppressWarnings("static-access")
	@Override
	public OauthClientDetailsDTO addAPIclient(OauthClientDetails client) {
		client.setSecret(randomStringUtils.randomAlphabetic(PASS_SIZE));
		oauthClientDetailsRepository.save(client);
		OauthClientDetailsDTO clientDTO = new OauthClientDetailsDTO(client); 
		return clientDTO;
	}

	@SuppressWarnings("static-access")
	@Override
	public OauthClientDetailsDTO updateAPIclient(String id, OauthClientDetails client) {
		OauthClientDetails updatedClient =  oauthClientDetailsRepository.findOne(id);
		updatedClient.setId(client.getId());
		updatedClient.setResourceId(client.getResourceId());
		updatedClient.setSecret(randomStringUtils.randomAlphabetic(PASS_SIZE));
		updatedClient.setScope(client.getScope());
		updatedClient.setGrantTypes(client.getGrantTypes());
		updatedClient.setRedirectUri(client.getRedirectUri());
		updatedClient.setAuthorities(client.getAuthorities());
		updatedClient.setAccessTokenValidity(client.getAccessTokenValidity());
		updatedClient.setRefreshTokenValidity(client.getRefreshTokenValidity());
		updatedClient.setAdditionalInfo(client.getAdditionalInfo());
		updatedClient.setAutoapprove(client.getAutoapprove());
		oauthClientDetailsRepository.save(updatedClient);
		OauthClientDetailsDTO clientDTO = new OauthClientDetailsDTO(updatedClient); 
		return clientDTO;
	}

	@Override
	public void deleteAPIclient(String id) {
		oauthClientDetailsRepository.delete(oauthClientDetailsRepository.findOne(id));
		
	}
	
	@PostConstruct
	private void setupDefaultAPIclients() {
		if (oauthClientDetailsRepository.count() > 0)
			return;
		
		oauthClientDetailsRepository.save(new OauthClientDetails(
				"crmClient1",
				null,
				"crmSuperSecret",
				"read,write,trust",
				"password,refresh_token",
				null,
				"ROLE_CLIENT,ROLE_TRUSTED_CLIENT",
				7776000, 2592000,
				null, null));
		oauthClientDetailsRepository.save(new OauthClientDetails(
				"chatId",
				null,
				"chatSecret",
				"read,write,trust",
				"password,refresh_token",
				null,
				"ROLE_CLIENT,ROLE_TRUSTED_CLIENT",
				7776000, 2592000,
				null, null));
		oauthClientDetailsRepository.save(new OauthClientDetails(
				"calendarId",
				null,
				"calendarSecret",
				"read,write,trust",
				"password,refresh_token",
				null,
				"ROLE_CLIENT,ROLE_TRUSTED_CLIENT",
				7776000, 2592000,
				null, null));
	}

}
