import React, {useEffect} from "react";
import {NavLink} from "react-router-dom";
import $ from 'jquery';

import "../css/Login.css";
import axios from "axios";
import {useNavigate} from "react-router";


function Login() {
    useEffect(() => {
        document.querySelector(".no-height").style.height = "auto";
    });

    const navigate = useNavigate();

    function loginClicked(email_, password_) {
        const r = $.ajax({
            type: "POST",
            url: 'http://localhost:8083/login',
            data: {
                email: email_,
                password: password_
            },
            success: function (response) {
                console.log("ura");
            },
            error: (error) => {
                console.log("error");
                //console.log(JSON.stringify(error));
            }
        }).then((respons) => {
            respons = $.ajax({
                url: 'http://localhost:8083/login',
                type: "GET",
                success: function(response, textStatus, jqXHR) {
                    console.log(jqXHR.status);
                        if (jqXHR.status === 200) {
                            console.log(response);
                            localStorage["user"] = response;
                            navigate('/profile');
                        }
                        else {
                            alert("Такого аккаунта не существует");
                        }
                },
                error: function(response) {
                }
            });


        })
    }
    // sendHttpGetRequest() {
    //     var self = this;
    //     var urlEndPoint = 'http://localhost:8083';
    //     $.ajax({
    //         url: urlEndPoint,
    //         type: "GET",
    //         success: function(response) {
    //             self.setState({servletGetResponse: response});
    //         },
    //         error: function(response) {
    //             self.setState({servletGetResponse: response});
    //         }
    //     });
    // }
    return (
        <div className="wrapper">
            <div className="main">
                <div className="form-section-1">
                    <div  className="left-form-login">
                        <div className="personal-header"><p className="personal-header-text">Вход в аккаунт</p>
                        </div>
                        <div className="input-box">
                            <label htmlFor="login-form-email">Email</label>
                            <input type="email" className="login-form-input-login" id="login-form-email" name="email" />
                        </div>
                        <div className="input-box">
                            <label htmlFor="login-form-date">Пароль</label>
                            <input type="password" className="login-form-input-login" id="login-form-password"
                                   name="password" />
                        </div>
                        <div className="button-box">
                            <input type="submit" value="Войти" className="save-button" onClick={() => loginClicked(document.getElementById("login-form-email").value, document.getElementById("login-form-password").value)} />
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
                    </div>
                </div>
            </div>
        </div>
    );
}


export default Login;