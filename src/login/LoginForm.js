import React, {Component} from 'react';
import './Login.css';
import UserLoginForm from './UserLoginForm';

class LoginForm extends Component{

    constructor(props){
        super(props);
        this.state = {
            status: true,
            errMess: null
        }
    }

    showErrMessage = (mes) => {
        this.setState({status: false, errMess: mes});
    }

    render = () => {
        
        return(
            <div className="login-container">
                <div className="login-message"></div>
                <div className="login-form">
                    <h1>Dream House Online</h1>
                    <div className="login-form-detial">
                        <UserLoginForm/>
                    </div>
                </div>
            </div>  
        );

    }

}
export default LoginForm;