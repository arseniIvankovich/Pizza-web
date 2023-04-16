package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.RoleDaoImpl;
import by.fpmibsu.Entity.Role;

import java.sql.SQLException;
import java.util.List;

public class RoleSetvice {
    final RoleDaoImpl roleDao;

    public RoleSetvice(RoleDaoImpl roleDao) {
        this.roleDao = roleDao;
    }

    public List<Role> findAll() throws SQLException {
        return roleDao.findAll();
    }

    public Role findEntityById(Long id) throws SQLException {
        return roleDao.findEntityById(id);
    }
}
