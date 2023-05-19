import React from "react";
import "../css/Index.css";
//import logo from './img/pizza-5-svgrepo-com.svg';

class Header extends React.Component {
    render() {
        return (
            <header className="header">

                <div className="wrapper">
                    <div className="header-wrapper">
                        <a href="/" className="header-logo-link">
                            <div className="header-logo-box">
                                <img src="./img/pizza-5-svgrepo-com.svg" alt="Pizzeria Logo"
                                     className="header-logo-pic" />
                                    <p className="header-logo-text">
                                        Pizzeria
                                    </p>
                            </div>
                        </a>

                        <nav className="header-nav">
                            <ul className="header-list">
                                <li className="header-item">
                                    <a href="#drinks" className="header-link js-scroll">Напитки</a>
                                </li>
                                <li className="header-item">
                                    <a href="/vacancy" id="vacancy-link" className="header-link"
                                       onClick="alert_vacancy()">Вакансии</a>
                                </li>
                            </ul>
                        </nav>

                        <div className="header-telephone-basket">
                            <div className="header-telephone-box">
                                <div className="call">
                                    <img src="../img/phone-svgrepo-com.svg" alt="Phone Logo"
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
                                <a href="/order" className="busket-link" onClick="alert_busket()">
                                    <div className="busket">
                                        <div className="busket-quantity">
                                            <div className="busket-button-quantity">
                                                <p className="busket-button-quantity-text">0</p>
                                            </div>
                                            <img src="../img/trolley-svgrepo-com%20(1).svg" alt="Busket Logo"
                                                 className="header-busket-pic" />
                                        </div>
                                        <p className="busket-text">
                                            Корзина
                                        </p>
                                    </div>
                                </a>
                                <div className="busket-button-orig">
                                    <p className="busket-button-text-orig">0.00 BYN</p>
                                </div>
                            </div>
                        </div>
                        <form method="post" action="/" className="login-form">
                            <input type="submit" className="profile-button-orig" name="profileButton" value="Войти" />
                        </form>
                    </div>
                </div>
            </header>
        );
    }
}

export default Header;