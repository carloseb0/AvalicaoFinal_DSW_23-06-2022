package br.upf.cineminhafinal.utils;

import br.upf.cineminhafinal.jwt.TokenJwt;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Carlos
 */
public class Utils {

    /**
     * Gerar uma chave a ser utilizada na assinatura do token.
     *
     * @return
     */
    public static Key gerarChave() {
        String keyString = "YzBhZTgwZWM2ZTI5Njk1YzQ3YWIxYzg0ZTk5NjkxZjQ4YzIwZGRkMGVlZWU4NTFiMjhjZDg5NzU5NTFjODQ3ZQ==";
        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "HmacSHA256");
        return key;
    }

    public static Date definirDataDeExpiracao(long tempoEmMinutos) {
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(tempoEmMinutos);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    
    public static String processarTokenJWT(String usuario) {
        Key chave = Utils.gerarChave();
        TokenJwt token = new TokenJwt(chave);
        //42.000 minutos equivalente a 30 dias
        Date dataExpiracao = Utils.definirDataDeExpiracao(42000L);
        return token.gerarToken(usuario, dataExpiracao);
    }

}

