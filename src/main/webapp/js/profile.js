
import '../jsx/jquery.js';

'use strict';



class LikeButton extends React.Component {
    constructor(props) {
        super(props);
        this.state = { liked: false };
    }

    render() {
        if (this.state.liked) {
            return 'You liked this.';
        }

        return (
            <button className={"logout-button"} onClick={() => this.setState({ liked: true })}>
                Нравится
            </button>
        );
    }
}

class ProfileForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            servletGetResponse: '',
            servletPostResponse: ''
        };

        this.sendHttpPostRequest = this.sendHttpPostRequest.bind(this);
        this.sendHttpGetRequest = this.sendHttpGetRequest.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        console.log("Mounted");
        console.log("Props name " + this.props.name);
    }

    sendHttpPostRequest() {
        var self = this;
        var urlEndPoint = '';
        $.ajax({
            type: "POST",
            url: urlEndPoint,
            success: function(response) {
                self.setState({servletPostResponse: response});
            },
            error: function(response) {
                self.setState({servletPostResponse: response});
            }
        });
    }

    sendHttpGetRequest() {
        var self = this;
        var urlEndPoint = '';
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
            <form onSubmit={this.handleSubmit} className="left-form">


                {/*<div id="like_button_container"></div>*/}


                <div className="personal-header"><p>Личные данные профиля</p></div>
                <div className="input-box">
                    <label htmlFor="login-form-street">Улица</label>
                    <input type="text" className="login-form-input" id="login-form-street" name="street" value={this.state.servletGetResponse} onChange={this.handleSubmit} />
                </div>
                <div className="input-box">
                    <label htmlFor="login-form-house">Дом</label>
                    <input type="number" className="login-form-input" id="login-form-house" name="house" />
                </div>
                <div className="input-box">
                    <label htmlFor="login-form-entrance">Подъезд</label>
                    <input type="number" className="login-form-input" id="login-form-entrance" name="entrance" />
                </div>

                <div className="input-box">
                    <label htmlFor="login-form-flat">Квартира</label>
                    <input type="number" className="login-form-input" id="login-form-flat" name="flat" />
                </div>
                <div className="input-box">
                    <label htmlFor="login-form-name">Имя и Фамилия</label>
                    <input type="text" className="login-form-input" id="login-form-name" name="firstSecond" />
                </div>
                <div className="input-box">
                    <label htmlFor="login-form-email">Email</label>
                    <input type="text" className="login-form-input" id="login-form-email" name="email" />
                </div>
                <div className="input-box">
                    <label htmlFor="login-form-phone">Телефон</label>
                    <input type="text" className="login-form-input" id="login-form-phone" name="telephone" />
                </div>

                <input type="submit" name="logout" className="save-changes-button" value="Get Request" onClick={() => {this.sendHttpGetRequest()}} />
            </form>
        );
    }
}

const domContainer1 = document.querySelector('.form-section');
const root1 = ReactDOM.createRoot(domContainer1);
root1.render(<ProfileForm />);

// const domContainer = document.querySelector('#like_button_container');
// const root = ReactDOM.createRoot(domContainer);
// root.render(<LikeButton />);

//
function loadData() {
    var busket = document.querySelector(".busket-button-text-orig");
    var busket_quantity = document.querySelector(".busket-button-quantity-text");

    if (typeof localStorage["busket-price"] === 'undefined') {
        localStorage["busket-price"] = '0.00';
    }
    if (typeof localStorage["busket-quantity"] === 'undefined') {
        localStorage["busket-quantity"] = '0';
    }
    busket.textContent = localStorage["busket-price"] + " BYN";
    busket_quantity.textContent = localStorage["busket-quantity"];

    //////////////////////////////////////////////////////////

    // let profileText = document.querySelector(".profile-json");
    // profileText = JSON.parse(profileText.value);
    // let form = document.querySelector(".left-form");
    // form.querySelector("#login-form-street").value = profileText.addresses.street;
    // form.querySelector("#login-form-house").value = profileText.addresses.houseNumber;
    // form.querySelector("#login-form-entrance").value = profileText.addresses.entrance;
    // form.querySelector("#login-form-flat").value = profileText.addresses.flatNumber;
    // form.querySelector("#login-form-name").value = profileText.firstName_lastName;
    // form.querySelector("#login-form-email").value = profileText.email;
    // form.querySelector("#login-form-phone").value = profileText.telephone;
}

loadData();