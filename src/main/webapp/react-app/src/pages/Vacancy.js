import React, {useEffect} from "react";

import '../css/Vacancy.css';
import {NavLink} from "react-router-dom";
import $ from "jquery";

function sendHttpGetRequest() {
    var self = this;
    var urlEndPoint = 'http://localhost:8083/vacancy';
    $.ajax({
        url: urlEndPoint,
        type: "GET",
        success: function(response) {
            console.log(response);
            //self.setState({servletGetResponse: response});
        },
        error: function(response) {
            console.log('error');
            //self.setState({servletGetResponse: response});
        }
    });
}



function sendHttpPostRequest() {
    //var self = this;
    var urlEndPoint = 'http://localhost:8083/vacancy';
    var object = {
        username: "abcdefgh",
        password: "12345678"
    };
    var objectJSON = JSON.stringify(object);
    $.ajax({
        type: "POST",
        url: urlEndPoint,
        dataType: 'json',
        data: {object: objectJSON},
        success: function(response) {
            console.log("ura")
            //self.setState({servletPostResponse: response});
        },
        error: (error) => {
            console.log(JSON.stringify(error));
        }
    });
}


function Vacancy() {

    return (
        <div className="wrapper">
            <div className="main">
                <div className="main-header">
                    <p className="main-header-text">
                        Список вакансий
                    </p>
                    <p className="main-header-text">
                        Фильтр вакансий
                    </p>
                </div>

                <div className="vacancy-section">
                    <div className="vacancy-card">
                        <div className="vacancy-card-pic-box">
                            <img src="./img/vacancies/pizzamaker.jpg" alt="Vacancy"
                                 className="vacancy-card-pic" />
                        </div>
                        <p className="vacancy-card-title">
                            Пиццер
                        </p>
                        <div className="description">
                            <ul type="disc" className="info-text-vacancy">
                                <li>зарплата от 1000 рублей в месяц;</li>
                                <li>испытательный срок - 30 дней;</li>
                                <li>профессиональный и карьерный рост;</li>
                                <li>почасовая оплата;</li>
                                <li>обучение на месте;</li>
                                <li>что ты будешь делать: готовить вкуснейшую пиццу,
                                    общаться с другими поварами,
                                    поддерживать порядок на рабочем месте;
                                </li>
                                <li>скидка 40% на продукцию компании.</li>
                            </ul>
                        </div>
                    </div>

                    <div className="vacancy-card">
                        <div className="vacancy-card-pic-box">
                            <img src="./img/vacancies/delivery_foot.jpeg" alt="Vacancy"
                                 className="vacancy-card-pic" />
                        </div>
                        <p className="vacancy-card-title">
                            Пеший курьер доставки
                        </p>
                        <div className="description">
                            <ul type="disc" className="info-text-vacancy">
                                <li>зарплата от 500 рублей в месяц;</li>
                                <li>испытательный срок - 60 дней;</li>
                                <li>профессиональный и карьерный рост;</li>
                                <li>почасовая оплата;</li>
                                <li>обучение на месте;</li>
                                <li>что ты будешь делать: доставлять заказы пешком и на велосипеде,
                                    общаться с другими курьерами,
                                    поддерживать порядок на рабочем месте;
                                </li>
                                <li>скидка 40% на продукцию компании;</li>
                                <li>ежемесячное премирование.</li>
                            </ul>
                        </div>
                    </div>

                    <div className="vacancy-card">
                        <div className="vacancy-card-pic-box">
                            <img src="./img/vacancies/delivery_car.jpg" alt="Vacancy"
                                 className="vacancy-card-pic" />
                        </div>
                        <p className="vacancy-card-title">
                            Курьер доставки
                        </p>
                        <div className="description">
                            <ul type="disc" className="info-text-vacancy">
                                <li>зарплата от 750 рублей в месяц;</li>
                                <li>испытательный срок - 45 дней;</li>
                                <li>профессиональный и карьерный рост;</li>
                                <li>почасовая оплата;</li>
                                <li>обучение на месте;</li>
                                <li>амортизация автомобиля до 200 рублей;</li>
                                <li>что ты будешь делать: доставлять заказы на своем автомобиле,
                                    общаться с другими участникамми коллектива,
                                    поддерживать порядок на рабочем месте;
                                </li>
                                <li>гибкий график;</li>
                                <li>скидка 40% на продукцию компании.</li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div className="main-header">
                    <p id="form" className="main-header-text">
                        Заполнить анкету
                    </p>
                </div>

                <div className="form-section">
                    <div className="left-form">
                        <input type="text" className="vacancy-json" />
                            <div className="input-box">
                                <label>Адрес:</label>
                                <p className="request-text" id="address">Ул. {JSON.parse(localStorage['user']).addresses.street + "   "}
                                    Д. {JSON.parse(localStorage['user']).addresses.houseNumber + "   "}
                                    П. {JSON.parse(localStorage['user']).addresses.entrance + "   "}
                                    Кв. {JSON.parse(localStorage['user']).addresses.flatNumber}</p>
                            </div>

                            <div className="input-box">
                                <label>Имя и Фамилия:</label>
                                <p className="request-text" id="name">{JSON.parse(localStorage['user']).firstName_lastName}</p>
                            </div>
                            <div className="input-box">
                                <label>Email:</label>
                                <p className="request-text" id="email">{JSON.parse(localStorage['user']).email}</p>
                            </div>
                            <div className="input-box">
                                <label>Телефон:</label>
                                <p className="request-text" id="phone">{JSON.parse(localStorage['user']).telephone}</p>
                            </div>
                            <div className="input-box">
                                <label htmlFor="login-form-position">Должность: </label>
                                <select className="login-form-select" id="login-form-position" name="choicer">
                                    <option value="Пиццер">Пиццер</option>
                                    <option value="Водитель-курьер">Водитель-курьер</option>
                                    <option value="Пеший курьер" selected="selected">Пеший курьер</option>
                                </select>
                            </div>
                            <div className="send-buttons">
                                <input type="submit" value="Отправить заявку" className="order-button" onClick={() => sendHttpGetRequest()} />
                                <NavLink to="/profile" className="change-profile">Изменить данные</NavLink>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Vacancy;