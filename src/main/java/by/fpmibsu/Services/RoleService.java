package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.RoleDaoImpl;
import by.fpmibsu.Entity.Role;

import java.sql.SQLException;
import java.util.List;

public class RoleService {
    public List<Role> findAll() throws SQLException {
        return new RoleDaoImpl().findAll();
    }

    public Role findEntityById(Long id) throws SQLException {
        return new RoleDaoImpl().findEntityById(id);
    }
}
