package com.ksquare.sso.domain;

public class OauthClientDetailsDTO {

	private String id;
	
	private String resourceId;
	
	private String scope;
	
	private String grantTypes;
	
	private String redirectUri;
	
	private String authorities;
	
	private int accessTokenValidity;
	
	private int refreshTokenValidity;
	
	private String additionalInfo;
	
	private String autoapprove;
	
	public OauthClientDetailsDTO() {
	}
	
	public OauthClientDetailsDTO(OauthClientDetails client) {
		this.id = client.getId();
		this.resourceId = client.getResourceId();
		this.scope = client.getScope();
		this.grantTypes = client.getGrantTypes();
		this.redirectUri = client.getRedirectUri();
		this.authorities = client.getAuthorities();
		this.accessTokenValidity = client.getAccessTokenValidity();
		this.refreshTokenValidity = client.getRefreshTokenValidity();
		this.additionalInfo = client.getAdditionalInfo();
		this.autoapprove = client.getAutoapprove();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getGrantTypes() {
		return grantTypes;
	}

	public void setGrantTypes(String grantTypes) {
		this.grantTypes = grantTypes;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public int getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(int accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public int getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(int refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getAutoapprove() {
		return autoapprove;
	}

	public void setAutoapprove(String autoapprove) {
		this.autoapprove = autoapprove;
	}	
}
