package solvd.inc.dao.implementation;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import solvd.inc.dao.CityMapper;
import solvd.inc.model.City;
import solvd.inc.config.ConnectionManager;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CityMapperImpl implements CityMapper {

    private final SqlSessionFactory sqlSessionFactory;

    public CityMapperImpl() {
        this.sqlSessionFactory = ConnectionManager.getInstance().getSessionFactory();
    }


    @Override
    public City create(City entity) {
        return null;
    }

    @Override
    public Optional<City> getById(Long id) {
        Connection connection = ConnectionManager.getInstance().acquireConnection().join();

        try (SqlSession session = sqlSessionFactory.openSession(connection)) {
            CityMapper cityMapper = session.getMapper(CityMapper.class);
            return cityMapper.getById(id);
        } finally {
            ConnectionManager.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public List<City> findAll() {
        Connection connection = ConnectionManager.getInstance().acquireConnection().join();

        try (SqlSession session = sqlSessionFactory.openSession(connection)) {
            CityMapper cityMapper = session.getMapper(CityMapper.class);
            return cityMapper.findAll();
        } finally {
            ConnectionManager.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public void update(City entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
