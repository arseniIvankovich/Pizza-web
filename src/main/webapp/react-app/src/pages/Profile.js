import React from "react";
import "../css/Profile.css";
import $ from 'jquery';

class Profile extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            servletGetResponse: '',
            servletPostResponse: 'abcde'
        };

        this.sendHttpPostRequest = this.sendHttpPostRequest.bind(this);
        this.sendHttpGetRequest = this.sendHttpGetRequest.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    // componentDidMount() {
    //     console.log("Mounted");
    //     console.log("Props name " + this.props.name);
    // }

    // componentDidMount() {
    //     // Simple POST request with a JSON body using fetch
    //     const requestOptions = {
    //         // mode: 'cors',
    //         method: 'POST',
    //         // headers: {
    //         //     'Content-Type': 'application/json',
    //         //     'Access-Control-Allow-Origin': '*'
    //         // },
    //         body: JSON.stringify({ title: 'React POST Request Example' })
    //     };
    //     fetch('http://localhost:8083', requestOptions)
    //         .then(response => response.json())
    //         .then(data => this.setState({ postId: data.id }));
    //     //fetch('http://localhost:8083', { method: 'POST' });
    // }

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

    handleSubmit(event) {
        console.log('A name was submitted: ' + event.target.value + ' - ' + event.target.name);
        event.preventDefault();
    }

    render() {
        return (
            <div>
                <div className="wrapper">
                    <div className="main">
                        <div className="form-section">
                            <form onSubmit={this.handleSubmit} action={"/"} method={"POST"} className="left-form">

                                <div className="personal-header"><p>Личные данные профиля</p></div>
                                <div className="input-box">
                                    <label htmlFor="login-form-street">Улица</label>
                                    <input type="text" className="login-form-input" id="login-form-street"
                                           name="street" value={this.state.servletPostResponse} onChange={this.handleSubmit} />
                                </div>
                                <div className="input-box">
                                    <label htmlFor="login-form-house">Дом</label>
                                    <input type="number" className="login-form-input" id="login-form-house"
                                           name="house" />
                                </div>
                                <div className="input-box">
                                    <label htmlFor="login-form-entrance">Подъезд</label>
                                    <input type="number" className="login-form-input" id="login-form-entrance"
                                           name="entrance" />
                                </div>

                                <div className="input-box">
                                    <label htmlFor="login-form-flat">Квартира</label>
                                    <input type="number" className="login-form-input" id="login-form-flat" name="flat" />
                                </div>
                                <div className="input-box">
                                    <label htmlFor="login-form-name">Имя и Фамилия</label>
                                    <input type="text" className="login-form-input" id="login-form-name"
                                           name="firstSecond" />
                                </div>
                                <div className="input-box">
                                    <label htmlFor="login-form-email">Email</label>
                                    <input type="text" className="login-form-input" id="login-form-email" name="email" />
                                </div>
                                <div className="input-box">
                                    <label htmlFor="login-form-phone">Телефон</label>
                                    <input type="text" className="login-form-input" id="login-form-phone"
                                           name="telephone" />
                                </div>

                                <input type="submit" name="logout" className="save-changes-button"
                                       value="Сохранить информацию" onClick={() => {this.sendHttpPostRequest();}} />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Profile;