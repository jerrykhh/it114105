import React, {Component} from 'react';
import UserSignUpForm from './UserSignUpForm';

class SignUpForm extends Component {
    
    constructor(props){
        super(props);
        this.state = {
            errMess: []
        }
    }

    showErrMessage = (mes) => {
        if (mes == null){
            this.setState({errMess: []});
        }else{
            this.setState({
                errMess: this.state.errMess.concat(mes)
            });
        }
    }

    render = () => {
        var mesList = null;
        if(this.state.errMess.length > 0){
            mesList = this.state.errMess.map( (message) => <div className="bs-callout bs-callout-danger"><h4>Registration Failed</h4>{message}</div>);
        }
        return(
            <div className="login-container">
                    
                    <div className="signup-form">
                        {mesList}
                        <h1>Dream House Online</h1>
                        <span>Create the customer account</span>
                        <div className="login-form-detial">
                            <UserSignUpForm argue={this.showErrMessage}/>
                        </div>
                    </div>
            </div>  
        );
    }

}

export default SignUpForm;