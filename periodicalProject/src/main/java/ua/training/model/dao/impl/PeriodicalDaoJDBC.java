package ua.training.model.dao.impl;

import ua.training.model.dao.PeriodicalDao;
import ua.training.model.dao.mapper.PeriodicalMapper;
import ua.training.model.dao.util.GeneralConnectionMethod;
import ua.training.model.entities.Periodical;
import ua.training.util.constant.Requests;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeriodicalDaoJDBC implements PeriodicalDao {

    private Connection connection;

    public PeriodicalDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(Periodical entity) {
        try(PreparedStatement ps = connection.prepareStatement(Requests.INSERT_PERIODICAL, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,entity.getName());
            ps.setInt(2,entity.getPrice());
            ps.setString(3,entity.getShortDescription());
            ps.executeUpdate();
            connection.commit();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Periodical findById(int id) {
        PeriodicalMapper periodicalMapper = new PeriodicalMapper();
        try(PreparedStatement ps = connection.prepareStatement(Requests.SELECT_ID_PERIODICAL)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return (periodicalMapper.getFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Periodical> findAll() {
        PeriodicalMapper periodicalMapper = new PeriodicalMapper();
        List<Periodical> resultList = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(Requests.SELECT_ALL_PERIODICAL)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultList.add(periodicalMapper.getFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public List<Periodical> findByUser(int idUser) {
        PeriodicalMapper periodicalMapper = new PeriodicalMapper();
        List<Periodical> resultList = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(Requests.SELECT_USER_PERIODICAL)){
            ps.setInt(1,idUser);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultList.add(periodicalMapper.getFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public List<Periodical> findByPayment(int idPayment) {
        PeriodicalMapper periodicalMapper = new PeriodicalMapper();
        List<Periodical> resultList = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(Requests.SELECT_PAYMENT_PERIODICAL)){
            ps.setInt(1,idPayment);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultList.add(periodicalMapper.getFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public List<Periodical> findFixedNumberOfPeriodicals(int limit, int offset) {
        List<Periodical> resultList = new ArrayList<>();
        PeriodicalMapper periodicalMapper = new PeriodicalMapper();

        try (PreparedStatement ps = connection.prepareStatement(Requests.SELECT_PERIODICALS_LIMIT)) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultList.add(periodicalMapper.getFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(Periodical entity, int id) {
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        GeneralConnectionMethod.close(connection);
    }

    @Override
    public void setAutoCommit(boolean state) {
        GeneralConnectionMethod.setAutoCommit(connection,state);
    }

    @Override
    public void rollback() {
        GeneralConnectionMethod.rollback(connection);
    }
}
