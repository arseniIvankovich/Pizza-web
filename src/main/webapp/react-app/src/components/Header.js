import React, {useEffect} from "react";
import {NavLink, Link} from "react-router-dom";

import "../css/Index.css";
import {useNavigate} from "react-router";

function Header() {
    useEffect(() => {
        if (typeof localStorage["pizza"] === 'undefined') {
            localStorage["pizza"] = JSON.stringify([]);
        }
        if (typeof localStorage["drinks"] === 'undefined') {
            localStorage["drinks"] = JSON.stringify([]);
        }

        if (typeof localStorage["busket-price"] === 'undefined') {
            localStorage["busket-price"] = '0.00';
        }
        if (typeof localStorage["busket-quantity"] === 'undefined') {
            localStorage["busket-quantity"] = '0';
        }

        var busket = document.querySelector(".busket-button-text-orig");
        var busket_quantity = document.querySelector(".busket-button-quantity-text");

        busket.textContent = localStorage["busket-price"] + " BYN";
        busket_quantity.textContent = localStorage["busket-quantity"];
    });

    const navigate = useNavigate();

    return (
        <header className="header">

            <div className="wrapper">
                <div className="header-wrapper">
                    <NavLink to="/" className="header-logo-link">
                        <div className="header-logo-box">
                            <img src="./img/pizza-5-svgrepo-com.svg" alt="Pizzeria Logo"
                                 className="header-logo-pic" />
                            <p className="header-logo-text-header">
                                Pizzeria
                            </p>
                        </div>
                    </NavLink>

                    <nav className="header-nav">
                        <ul className="header-list">
                            <li className="header-item">
                                <a href="#drinks" className="header-link js-scroll">Напитки</a>
                            </li>
                            <li className="header-item">
                                <NavLink to="/vacancy" id="vacancy-link" className="header-link">Вакансии</NavLink>
                            </li>
                        </ul>
                    </nav>

                    <div className="header-telephone-basket">
                        <div className="header-telephone-box">
                            <div className="call">
                                <img src="./img/phone-svgrepo-com.svg" alt="Phone Logo"
                                     className="header-phone-pic" />
                                <p className="call-number">
                                    1442
                                </p>
                            </div>
                            <p className="work-time">
                                8:00-23:00
                            </p>
                        </div>

                        <div className="header-busket-box">
                            <NavLink to={"/order"} className="busket-link" >
                                <div className="busket">
                                    <div className="busket-quantity">
                                        <div className="busket-button-quantity">
                                            <p className="busket-button-quantity-text">${localStorage["busket-quantity"]}</p>
                                        </div>
                                        <img src="./img/trolley-svgrepo-com.svg" alt="Busket Logo"
                                             className="header-busket-pic" />
                                    </div>
                                    <p className="busket-text">
                                        Корзина
                                    </p>
                                </div>
                            </NavLink>
                            <div className="busket-button-orig">
                                <p className="busket-button-text-orig">${localStorage["busket-price"] + " BYN"}</p>
                            </div>
                        </div>
                    </div>
                    <form method="post" action="/" className="login-form">
                        <input type="submit" className="profile-button-orig" name="profileButton" value="Войти" onClick={() => navigate('/login')} />
                    </form>
                </div>
            </div>
        </header>
    );
}

export default Header;