import React from "react";
import "../css/Index.css";

class Footer extends React.Component {
    render() {
        return (
            <footer className="footer">
                <div className="wrapper">
                    <div className="footer-wrapper">
                        <div className="footer-info">
                            <div className="piece-of-info">
                                <p className="info-title">
                                    О нас
                                </p>
                                <ul type="disc" className="info-text">
                                    <li>первая пиццерия, созданная на ФПМИ</li>
                                    <li>различные контакты</li>
                                </ul>
                            </div>

                            <div className="piece-of-info">
                                <p className="info-title">
                                    Доставка
                                </p>
                                <ul type="disc" className="info-text">
                                    <li>что-то</li>
                                    <li>что-то</li>
                                </ul>
                            </div>

                            <div className="piece-of-info">
                                <p className="info-title">
                                    Спонсоры
                                </p>
                                <ul type="disc" className="info-text">
                                    <li>что-то</li>
                                    <li>что-то</li>
                                </ul>
                            </div>

                            <div className="piece-of-info">
                                <p className="info-title">
                                    Соотрудничающие компании
                                </p>
                                <ul type="disc" className="info-text">
                                    <li>что-то</li>
                                    <li>что-то</li>
                                </ul>
                            </div>
                        </div>
                        <div className="footer-icons">
                            <div className="social-networks">
                                <img src="../img/instagram-svgrepo-com.svg" alt="Instagram" className="network-pic" />
                                    <img src="../img/facebook-svgrepo-com%20(1).svg" alt="Facebook"
                                         className="network-pic" />
                                        <img src="../img/twitter-svgrepo-com.svg" alt="Twitter" className="network-pic" />
                            </div>
                            <div className="sponsors">
                                <img src="../img/visa-svgrepo-com.svg" alt="Visa" className="sponsor-pic" />
                                    <img src="../img/mastercard-svgrepo-com.svg" alt="Visa" className="sponsor-pic" />
                            </div>
                        </div>
                    </div>
                </div>
            </footer>
        );
    }
}

export default Footer;