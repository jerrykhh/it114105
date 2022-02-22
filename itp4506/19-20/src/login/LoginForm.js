import React, { Component } from 'react';
import queryString from 'query-string';
import './Login.css';
import UserLoginForm from './UserLoginForm';

class LoginForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            errMess: [],
            sucMess: []
        };   
    }

    showErrMessage = (mes, type) => {
        //0: error, 1:succesful
        if (mes == null) {
            this.setState({ sucMess: [], errMess:[] });
        } else {
            switch (type) {
                case 1:
                    this.setState({
                        sucMess: this.state.sucMess.concat(mes)
                    });
                    break;

                default:
                    this.setState({
                        errMess: this.state.errMess.concat(mes)
                    });
                    break;
            }
        }
    }

    componentDidMount = () =>{
        const values = queryString.parse(this.props.location.search)
        let username = values.reg;
        if(username !== undefined){
            this.setState({
                sucMess: this.state.sucMess.concat("username: " + decodeURI(username) + " is created")
            });
        }
    }


    render = () => {
        var mesList = null;
        if (this.state.errMess.length > 0) {
        mesList = <div className="bs-callout bs-callout-danger"><h4>Login Failed</h4>{this.state.errMess[this.state.errMess.length - 1]}</div>;
        }else if(this.state.sucMess.length > 0){
            mesList = <div className="bs-callout bs-callout-success"><h4>Registration Successful</h4>{this.state.sucMess[this.state.sucMess.length - 1]}</div>;
        }
        return (
            <div className="login-container">
                <div className="login-form">
                    {mesList}
                    <h1>Dream House Online</h1>
                    <div className="login-form-detial">
                        <UserLoginForm argue={this.showErrMessage} />
                    </div>
                </div>
            </div>
        );

    }

}
export default LoginForm;