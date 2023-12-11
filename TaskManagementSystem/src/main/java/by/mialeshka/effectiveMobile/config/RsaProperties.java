package by.mialeshka.effectiveMobile.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix ="rsa")
@Getter
@Setter
public class RsaProperties {
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;
}


