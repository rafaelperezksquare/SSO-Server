package com.ksquare.sso.service;

import java.util.List;

import com.ksquare.sso.domain.OauthClientDetails;
import com.ksquare.sso.domain.OauthClientDetailsDTO;

public interface OauthClientDetailsService {
	List<OauthClientDetailsDTO> getAPIclients();
	OauthClientDetailsDTO getAPIclient(String id);
	OauthClientDetailsDTO addAPIclient(OauthClientDetails client);
	OauthClientDetailsDTO updateAPIclient(String id, OauthClientDetails client);
	void deleteAPIclient(String id);
}
