package ua.training.model.dao.impl;

import ua.training.model.dao.ArticleDao;
import ua.training.model.dao.mapper.ArticleMapper;
import ua.training.model.dao.util.GeneralConnectionMethod;
import ua.training.model.entities.Article;
import ua.training.util.constant.Requests;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoJDBC implements ArticleDao {

    private Connection connection;

    public ArticleDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(Article entity) {
        try(PreparedStatement ps = connection.prepareStatement(Requests.INSERT_ARTICLE, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,entity.getName());
            ps.setDate(2, Date.valueOf(entity.getDate()));
            ps.setInt(3,entity.getIdPeriodical());
            ps.setString(4,entity.getText());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Article findById(int id) {
        ArticleMapper articleMapper = new ArticleMapper();
        try(PreparedStatement ps = connection.prepareStatement(Requests.SELECT_ID_ARTICLE)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return (articleMapper.getFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Article> findAll() {
        ArticleMapper articleMapper = new ArticleMapper();
        List<Article> resultList = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(Requests.SELECT_ALL_ARTICLE)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultList.add(articleMapper.getFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public List<Article> findByPeriodical(int idPeriodical) {
        ArticleMapper articleMapper = new ArticleMapper();
        List<Article> resultList = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(Requests.SELECT_IDPERIODICAL_ARTICLE)){
            ps.setInt(1,idPeriodical);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultList.add(articleMapper.getFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public List<Article> findFixedNumberOfArticles(int limit, int offset) {
        List<Article> resultList = new ArrayList<>();
        ArticleMapper articleMapper = new ArticleMapper();

        try (PreparedStatement ps = connection.prepareStatement(Requests.SELECT_ARTICLES_LIMIT)) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultList.add(articleMapper.getFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public List<Article> findByPeriodicalFixedNumberOfArticles(int id, int limit, int offset) {
        List<Article> resultList = new ArrayList<>();
        ArticleMapper articleMapper = new ArticleMapper();

        try (PreparedStatement ps = connection.prepareStatement(Requests.SELECT_ARTICLES_LIMIT_BY_ID)) {
            ps.setInt(1, id);
            ps.setInt(2, limit);
            ps.setInt(3, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultList.add(articleMapper.getFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(Article entity, int id) {

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
