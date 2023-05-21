import React, {useEffect} from "react";
import "../css/Login.css";
import {NavLink} from "react-router-dom";


function Login() {
    useEffect(() => {
        document.querySelector(".no-height").style.height = "auto";
    });

    return (
        <div className="wrapper">
            <div className="main">
                <div className="form-section-1">
                    <form method="post" action="/login" className="left-form-login">
                        <div className="personal-header"><p className="personal-header-text">Вход в аккаунт</p>
                        </div>
                        <div className="input-box">
                            <label htmlFor="login-form-email">Email</label>
                            <input type="email" className="login-form-input-login" id="login-form-email" name="email" />
                        </div>
                        <div className="input-box">
                            <label htmlFor="login-form-date">Пароль</label>
                            <input type="password" className="login-form-input-login" id="login-form-date"
                                   name="password" />
                        </div>
                        <div className="button-box">
                            <input type="submit" value="Войти" className="save-button" />
                        </div>
                        <div>
                            <NavLink to={"/"} className="login-form-register-ref">
                                Вернуться на главную
                            </NavLink>
                        </div>
                        <div>
                            <NavLink to="/register" className="login-form-register-ref">
                                Зарегистрироваться
                            </NavLink>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}


export default Login;