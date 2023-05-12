package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.RoleDaoImpl;
import by.fpmibsu.Dao.RoleDao;
import by.fpmibsu.Entity.Role;

import java.sql.SQLException;

public class RoleService {
    private RoleDao roleDao;

    public RoleService() {
        this.roleDao = new RoleDaoImpl();
    }

    public Role findEntityById(Long id)  {
        return new RoleDaoImpl().findEntityById(id);
    }
}
