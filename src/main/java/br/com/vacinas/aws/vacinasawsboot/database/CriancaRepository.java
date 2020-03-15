package br.com.vacinas.aws.vacinasawsboot.database;

import br.com.vacinas.aws.vacinasawsboot.Crianca;
import br.com.vacinas.aws.vacinasawsboot.ErroDeAmbienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@PropertySource("classpath:crianca.sql.properties")
public class CriancaRepository {

    @Value("${inserir.crianca}")
    String inserirCrianca;

    @Value("${buscar.crianca.por.nome}")
    String buscarCriancaPorNome;

    @Value("${buscar.crianca.por.userid}")
    String buscarCriancaPorUserId;

    @Autowired
    JdbcTemplate jdbcTemplate;

    String classe = CriancaRepository.class.getName() + ": ";

    public void salvar(Crianca crianca, String userId) {

        try {
            jdbcTemplate.update(inserirCrianca, crianca.getNome(), crianca.getDataNascimento(), userId);
        } catch (Exception e) {
            throw new ErroDeAmbienteException(CriancaRepository.class, e.getMessage());
        }
    }

    public List<Crianca> buscarCriancasPorUserId(String userId) {

        try {
            return jdbcTemplate.query(buscarCriancaPorUserId, new Object[]{userId}, new CriancaRowMapper());
        } catch (Exception e) {
            throw new ErroDeAmbienteException(classe + e.getMessage());
        }

    }

    public Crianca buscarCriancaPorNome(String nome) {
        try {
            return jdbcTemplate.queryForObject(buscarCriancaPorNome, new Object[]{nome}, new CriancaRowMapper());
        } catch (Exception e) {
            throw new ErroDeAmbienteException(classe + e.getMessage());
        }
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
