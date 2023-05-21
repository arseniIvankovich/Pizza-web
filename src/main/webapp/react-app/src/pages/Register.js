import React from "react";
import "../css/Register.css";
import {NavLink} from "react-router-dom";

function Register() {

    return (
        <div className="wrapper">
            <div className="main-register">
                <div className="form-section-register">
                    <form method="post" action="/register" className="left-form-register">
                        <div className="personal-header"><p className="personal-header-text">Регистрация</p></div>
                        <div className="input-box-register">
                            <label htmlFor="login-form-street">Улица</label>
                            <input type="text" className="login-form-input-register" id="login-form-street" name="street" />
                        </div>
                        <div className="input-box-register">
                            <label htmlFor="login-form-house">Дом</label>
                            <input type="text" className="login-form-input-register" id="login-form-house" name="house" />
                        </div>
                        <div className="input-box-register">
                            <label htmlFor="login-form-entrance">Подъезд</label>
                            <input type="number" className="login-form-input-register" id="login-form-entrance" name="entrance" />
                        </div>

                        <div className="input-box-register">
                            <label htmlFor="login-form-flat">Квартира</label>
                            <input type="number" className="login-form-input-register" id="login-form-flat" name="flat" />
                        </div>
                        <div className="input-box-register">
                            <label htmlFor="login-form-name">Имя и Фамилия</label>
                            <input type="text" className="login-form-input-register" id="login-form-name" name="firstSecondName" />
                        </div>
                        <div className="input-box-register">
                            <label htmlFor="login-form-email">Email</label>
                            <input type="email" className="login-form-input-register" id="login-form-email" name="email" />
                        </div>
                        <div className="input-box-register">
                            <label htmlFor="login-form-phone">Телефон</label>
                            <input type="text" className="login-form-input-register" id="login-form-phone" name="telephone" />
                        </div>
                        <div className="input-box-register">
                            <label htmlFor="login-form-date">Пароль</label>
                            <input type="text" className="login-form-input-register" id="login-form-date" name="password" />
                        </div>
                        <div className="button-box">
                            <input type="submit" value="Зарегистрироваться" className="save-button" />
                        </div>
                        <div>
                            <NavLink to="/" className="login-form-register-ref">
                                Вернуться на главную
                            </NavLink>
                        </div>
                        <div>
                            <NavLink to="/login" className="login-form-register-ref">
                                Уже есть аккаунт?
                            </NavLink>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default Register;