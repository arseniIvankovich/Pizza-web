var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

import '../jsx/jquery.js';

'use strict';

var LikeButton = function (_React$Component) {
    _inherits(LikeButton, _React$Component);

    function LikeButton(props) {
        _classCallCheck(this, LikeButton);

        var _this = _possibleConstructorReturn(this, (LikeButton.__proto__ || Object.getPrototypeOf(LikeButton)).call(this, props));

        _this.state = { liked: false };
        return _this;
    }

    _createClass(LikeButton, [{
        key: 'render',
        value: function render() {
            var _this2 = this;

            if (this.state.liked) {
                return 'You liked this.';
            }

            return React.createElement(
                'button',
                { className: "logout-button", onClick: function onClick() {
                        return _this2.setState({ liked: true });
                    } },
                '\u041D\u0440\u0430\u0432\u0438\u0442\u0441\u044F'
            );
        }
    }]);

    return LikeButton;
}(React.Component);

var ProfileForm = function (_React$Component2) {
    _inherits(ProfileForm, _React$Component2);

    function ProfileForm(props) {
        _classCallCheck(this, ProfileForm);

        var _this3 = _possibleConstructorReturn(this, (ProfileForm.__proto__ || Object.getPrototypeOf(ProfileForm)).call(this, props));

        _this3.state = {
            servletGetResponse: '',
            servletPostResponse: ''
        };

        _this3.sendHttpPostRequest = _this3.sendHttpPostRequest.bind(_this3);
        _this3.sendHttpGetRequest = _this3.sendHttpGetRequest.bind(_this3);
        _this3.handleSubmit = _this3.handleSubmit.bind(_this3);
        return _this3;
    }

    // constructor(props) {
    //     super(props);
    //     this.state = {
    //         posts: [],
    //     };
    // }

    _createClass(ProfileForm, [{
        key: 'componentDidMount',
        value: function componentDidMount() {
            console.log("Mounted");
            console.log("Props name " + this.props.name);
        }

        // componentDidMount() {
        //     fetch('http://localhost:8083')
        //         .then((response) => {
        //             response;
        //             console.log(response);
        //         })
        //         .then((data) => this.setState({ posts: data }))
        //         .catch((error) => console.log(error));
        // }

    }, {
        key: 'sendHttpPostRequest',
        value: function sendHttpPostRequest() {
            var self = this;
            var urlEndPoint = '';
            $.ajax({
                type: "POST",
                url: urlEndPoint,
                success: function success(response) {
                    self.setState({ servletPostResponse: response });
                },
                error: function error(response) {
                    self.setState({ servletPostResponse: response });
                }
            });
        }
    }, {
        key: 'sendHttpGetRequest',
        value: function sendHttpGetRequest() {
            var self = this;
            var urlEndPoint = 'http://localhost:8083';
            $.ajax({
                url: urlEndPoint,
                type: "GET",
                success: function success(response) {
                    self.setState({ servletGetResponse: response });
                },
                error: function error(response) {
                    self.setState({ servletGetResponse: response });
                }
            });
        }
    }, {
        key: 'handleSubmit',
        value: function handleSubmit(event) {
            console.log('A name was submitted: ' + event.target.value + ' - ' + event.target.name);
            event.preventDefault();
        }
    }, {
        key: 'render',
        value: function render() {
            var _this4 = this;

            var posts = this.state.posts;

            console.log(this.state.servletGetResponse);

            return React.createElement(
                'form',
                { onSubmit: this.handleSubmit, className: 'left-form' },
                React.createElement(
                    'div',
                    { className: 'personal-header' },
                    React.createElement(
                        'p',
                        null,
                        '\u041B\u0438\u0447\u043D\u044B\u0435 \u0434\u0430\u043D\u043D\u044B\u0435 \u043F\u0440\u043E\u0444\u0438\u043B\u044F'
                    )
                ),
                React.createElement(
                    'div',
                    { className: 'input-box' },
                    React.createElement(
                        'label',
                        { htmlFor: 'login-form-street' },
                        '\u0423\u043B\u0438\u0446\u0430'
                    ),
                    React.createElement('input', { type: 'text', className: 'login-form-input', id: 'login-form-street', name: 'street', value: this.state.servletGetResponse, onChange: this.handleSubmit })
                ),
                React.createElement(
                    'div',
                    { className: 'input-box' },
                    React.createElement(
                        'label',
                        { htmlFor: 'login-form-house' },
                        '\u0414\u043E\u043C'
                    ),
                    React.createElement('input', { type: 'number', className: 'login-form-input', id: 'login-form-house', name: 'house' })
                ),
                React.createElement(
                    'div',
                    { className: 'input-box' },
                    React.createElement(
                        'label',
                        { htmlFor: 'login-form-entrance' },
                        '\u041F\u043E\u0434\u044A\u0435\u0437\u0434'
                    ),
                    React.createElement('input', { type: 'number', className: 'login-form-input', id: 'login-form-entrance', name: 'entrance' })
                ),
                React.createElement(
                    'div',
                    { className: 'input-box' },
                    React.createElement(
                        'label',
                        { htmlFor: 'login-form-flat' },
                        '\u041A\u0432\u0430\u0440\u0442\u0438\u0440\u0430'
                    ),
                    React.createElement('input', { type: 'number', className: 'login-form-input', id: 'login-form-flat', name: 'flat' })
                ),
                React.createElement(
                    'div',
                    { className: 'input-box' },
                    React.createElement(
                        'label',
                        { htmlFor: 'login-form-name' },
                        '\u0418\u043C\u044F \u0438 \u0424\u0430\u043C\u0438\u043B\u0438\u044F'
                    ),
                    React.createElement('input', { type: 'text', className: 'login-form-input', id: 'login-form-name', name: 'firstSecond' })
                ),
                React.createElement(
                    'div',
                    { className: 'input-box' },
                    React.createElement(
                        'label',
                        { htmlFor: 'login-form-email' },
                        'Email'
                    ),
                    React.createElement('input', { type: 'text', className: 'login-form-input', id: 'login-form-email', name: 'email' })
                ),
                React.createElement(
                    'div',
                    { className: 'input-box' },
                    React.createElement(
                        'label',
                        { htmlFor: 'login-form-phone' },
                        '\u0422\u0435\u043B\u0435\u0444\u043E\u043D'
                    ),
                    React.createElement('input', { type: 'text', className: 'login-form-input', id: 'login-form-phone', name: 'telephone' })
                ),
                React.createElement('input', { type: 'submit', name: 'logout', className: 'save-changes-button', value: 'Get Request', onClick: function onClick() {
                        _this4.sendHttpGetRequest();
                    } })
            );
        }
    }]);

    return ProfileForm;
}(React.Component);

var domContainer1 = document.querySelector('.form-section');
var root1 = ReactDOM.createRoot(domContainer1);
root1.render(React.createElement(ProfileForm, null));

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