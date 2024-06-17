package br.upf.cineminhafinal.jwt;

import br.upf.cineminhafinal.utils.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.security.Key;
import java.util.Date;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class TokenJwt {

    private static Key chave;
    private String jwt;

    public TokenJwt(Key chave) {
        this.chave = chave;
    }

    public static boolean validarToken(String token) {
        chave = Utils.gerarChave();
        boolean tokenValido = false;
        if (token != null) {
            try {
                Jwts.parser().setSigningKey(chave).parseClaimsJws(token);
                tokenValido = true;
            } catch (ExpiredJwtException e) {
                throw new WebApplicationException(Response.status(403).entity("Token expirado!").build());
            } catch (MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
                throw new WebApplicationException(Response.status(401).entity("Token inválido!").build());
            }
        } else {
            throw new WebApplicationException(Response.status(400).entity("Nenhum cabeçalho de autenticação presente!").build());
        }
        return tokenValido;
    }

    public String gerarToken(String nomeUsuario, Date dataExpiracao) {
        jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT") //definindo cabeçalho
                .setSubject(nomeUsuario) //assunto do token
                .setIssuer("upf") //quem é o emissor do token
                .setIssuedAt(new Date()) //data de criação
                .claim("password", "sdlkjsdoijonpvf65v4e6fv5e6ver")
                .setExpiration(dataExpiracao) //data de expiração do token
                .signWith(SignatureAlgorithm.HS256, chave) //assinatura do token
                .compact(); //contruir o JWT                
        return jwt;
    }

    public String recuperarSubjectDoToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(chave).parseClaimsJws(token);
        return claimsJws.getBody().getSubject();
    }

    public String recuperarIssuerDoToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(chave).parseClaimsJws(token);
        return claimsJws.getBody().getIssuer();
    }

    public String resuperarClaim(String token, String claim) {
        String subject = "";
        if (token != null && !token.equals("")) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(chave).parseClaimsJws(token);
            subject = claimsJws.getBody().get(claim).toString();
        }
        return subject;
    }

}
