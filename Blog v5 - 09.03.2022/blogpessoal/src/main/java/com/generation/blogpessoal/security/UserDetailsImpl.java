package com.generation.blogpessoal.security;

import java.util.Collection;
import java.util.List;

import com.generation.blogpessoal.model.Usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;

	public UserDetailsImpl(Usuario usuario) {
		this.userName = usuario.getUsuario();
		this.password = usuario.getSenha();
	}

	public UserDetailsImpl() {	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {

		return userName;
	}
	
	@Override
	public boolean isAccountNonExpired() { /*Indica que a conta nao tem limite de prazo de uso*/
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { /*Indica que a conta nao esta bloqueada*/
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() { /*Indica que os direitos de acesso nao expiram em algum momento*/
		return true;
	}

	@Override
	public boolean isEnabled() { /*Indica se a conta esta Ativada*/
		return true;
	}
}