import React from "react";
import "../css/Profile.css";
import $ from 'jquery';

class Profile extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            //user:{"id":24,"firstName_lastName":"Мария Мишина","password":"$2a$10$lLSQAdY/6LDFi9.2yTomnOL0iYQeBXi88U/cann9qq5SqtEC/8Jye","addresses":{"id":47,"street":"Мира","houseNumber":16,"entrance":5,"flatNumber":279,"addressID":47},"order":{"id":55,"drinks":[{"id":9,"name":"Ballentine's","counter":2,"capacity":0.5,"price":62.99,"drinkID":9}],"pizzas":[],"status":false,"deliveryDate":1684311147706,"paymentMethod":"Наличными"},"role":{"id":3,"role":"Пользователь"},"userId":24,"email":"1111@gmail.com","telephone":"11111"},
            servletGetResponse: '',
            servletPostResponse: 'abcde'
        };

        this.sendHttpPostRequest = this.sendHttpPostRequest.bind(this);
        this.sendHttpGetRequest = this.sendHttpGetRequest.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    sendHttpPostRequest() {
        var self = this;
        var urlEndPoint = 'http://localhost:8083?id=12345&name=2321';
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
                self.setState({servletPostResponse: response});
            },
            error: (error) => {
                console.log(JSON.stringify(error));
                console.log(this.state.servletPostResponse);
            }
        });
    }

    sendHttpGetRequest() {
        var self = this;
        var urlEndPoint = 'http://localhost:8083';
        $.ajax({
            url: urlEndPoint,
            type: "GET",
            success: function(response) {
                self.setState({servletGetResponse: response});
            },
            error: function(response) {
                self.setState({servletGetResponse: response});
            }
        });
    }

    componentDidMount() {
        $.ajax({
            url: 'http://localhost:8083/profile',
            type: "GET",
            headers:{
                contentType: "application/json; charset=utf-8",
            },
            success: function(response, textStatus, jqXHR) {
                console.log(response);
                localStorage["user"] = response;
            },
            error: function(response) {
            }
        });
    }

    handleSubmit(event) {
        console.log('A name was submitted: ' + event.target.value + ' - ' + event.target.name);
        event.preventDefault();
    }

    render() {
        return (
            <div>
                <div className="wrapper">
                    <div className="main-profile">
                        <div className="form-section-profile">
                            <div className="left-form-profile">

                                {/*{"id":24,"firstName_lastName":"Мария Мишина","password":"$2a$10$lLSQAdY/6LDFi9.2yTomnOL0iYQeBXi88U/cann9qq5SqtEC/8Jye","addresses":{"id":47,"street":"Мира","houseNumber":16,"entrance":5,"flatNumber":279,"addressID":47},"order":{"id":55,"drinks":[{"id":9,"name":"Ballentine's","counter":2,"capacity":0.5,"price":62.99,"drinkID":9}],"pizzas":[],"status":false,"deliveryDate":1684311147706,"paymentMethod":"Наличными"},"role":{"id":3,"role":"Пользователь"},"userId":24,"email":"1111@gmail.com","telephone":"11111"}*/}

                                <div className="personal-header-profile"><p>Личные данные профиля</p></div>
                                <div className="input-box-profile">
                                    <label htmlFor="login-form-street">Улица</label>
                                    <input type="text" className="login-form-input-profile" id="login-form-street"
                                           // name="street" value={""} onChange={this.handleSubmit} />
                                           name="street" value={JSON.parse(localStorage['user']).addresses.street} onChange={this.handleSubmit} />
                                </div>
                                <div className="input-box-profile">
                                    <label htmlFor="login-form-house">Дом</label>
                                    <input type="number" className="login-form-input-profile" id="login-form-house"
                                           // name="house" value={""} />
                                           name="house" value={JSON.parse(localStorage['user']).addresses.houseNumber} />
                                </div>
                                <div className="input-box-profile">
                                    <label htmlFor="login-form-entrance">Подъезд</label>
                                    <input type="number" className="login-form-input-profile" id="login-form-entrance"
                                           // name="entrance" value={""} />
                                           name="entrance" value={JSON.parse(localStorage['user']).addresses.entrance} />
                                </div>

                                <div className="input-box-profile">
                                    <label htmlFor="login-form-flat">Квартира</label>
                                    {/*<input type="number" className="login-form-input-profile" id="login-form-flat" name="flat" value={""} />*/}
                                    <input type="number" className="login-form-input-profile" id="login-form-flat" name="flat" value={JSON.parse(localStorage['user']).addresses.flatNumber} />
                                </div>
                                <div className="input-box-profile">
                                    <label htmlFor="login-form-name">Имя и Фамилия</label>
                                    <input type="text" className="login-form-input-profile" id="login-form-name"
                                           // name="firstSecond" value={""} />
                                           name="firstSecond" value={JSON.parse(localStorage['user']).firstName_lastName} />
                                </div>
                                <div className="input-box-profile">
                                    <label htmlFor="login-form-email">Email</label>
                                    {/*<input type="text" className="login-form-input-profile" id="login-form-email" name="email" value={""} />*/}
                                    <input type="text" className="login-form-input-profile" id="login-form-email" name="email" value={JSON.parse(localStorage['user']).email} />
                                </div>
                                <div className="input-box-profile">
                                    <label htmlFor="login-form-phone">Телефон</label>
                                    <input type="text" className="login-form-input-profile" id="login-form-phone"
                                           // name="telephone" value={""} />
                                           name="telephone" value={JSON.parse(localStorage['user']).telephone} />
                                </div>

                                <input type="submit" name="logout" className="save-changes-button"
                                       value="Сохранить информацию" onClick={() => {this.sendHttpPostRequest();}} />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Profile;