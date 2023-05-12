package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.PizzaDaoImpl;
import by.fpmibsu.Dao.DaoImpl.RoleDaoImpl;
import by.fpmibsu.Dao.PizzaDao;
import by.fpmibsu.Dao.RoleDao;
import by.fpmibsu.Entity.Role;

import java.sql.SQLException;
import java.util.List;

public class RoleService {
    private RoleDao roleDao;

    public RoleService() {
        this.roleDao = new RoleDaoImpl();
    }

    public Role findEntityById(Long id) throws SQLException {
        return new RoleDaoImpl().findEntityById(id);
    }
}
