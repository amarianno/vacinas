package br.com.vacinas.aws.vacinasawsboot.database;

import br.com.vacinas.aws.vacinasawsboot.Crianca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@PropertySource("classpath:crianca.sql.properties")
public class CriancaRepository {

    @Value("${inserir.crianca}")
    String inserirCrianca;

    @Value("${buscar.crianca.por.nome}")
    String buscarCriancaPorNome;


    @Autowired
    JdbcTemplate jdbcTemplate;

    public void salvar(Crianca crianca, String userId) {

        try {
            jdbcTemplate.update(inserirCrianca, crianca.getNome(), crianca.getDataNascimento(), userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Crianca buscarCriancaPorNome(String nome) {
        try {
            return jdbcTemplate.queryForObject(buscarCriancaPorNome, new Object[]{nome}, new CriancaRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    class CriancaRowMapper implements RowMapper<Crianca> {

        @Override
        public Crianca mapRow(ResultSet rs, int rowNum) throws SQLException {

            return Crianca
                    .builder()
                    .id(rs.getLong("id"))
                    .nome(rs.getString("nome"))
                    .dataNascimento(rs.getTimestamp("data_nascimento").toLocalDateTime().toLocalDate())
                    .build();
        }
    }

}
