package com.jybl.admin.security;

import com.jybl.admin.util.EncryptUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CredentialsMatcher extends SimpleCredentialsMatcher {

    private final static Logger LOGGER = LoggerFactory.getLogger(CredentialsMatcher.class);

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
        String username = authcToken.getUsername();
        Object tokenCredentials = String.valueOf(authcToken.getPassword());
        LOGGER.info(authcToken.getPassword().toString());
        Object accountCredentials = EncryptUtils.md5(username + getCredentials(info).toString());
        return accountCredentials.equals(tokenCredentials);
    }
}
