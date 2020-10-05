import React, {Component} from 'react';
import UserSignUpForm from './UserSignUpForm';

class SignUpForm extends Component {
    
    constructor(props){
        super(props);
        this.state = {
            status: true,
            errMess: []
        }
    }

    showErrMessage = (mes) => {
        console.log("show: " + mes);
        if (mes == null){
            this.setState({errMess: []});
            console.log(this.state.errMess);
        }else{
            this.setState({
                errMess: this.state.errMess.concat(mes)
            });
        }
        this.setState({status: false});
    }

    render = () => {
        var mesList = null;
        console.log(this.state);
        if(this.state.errMess.length > 0){
            mesList = this.state.errMess.map( (message) => <div className="bs-callout bs-callout-danger"><h4>Registration Failed</h4>{message}</div>);
        }
        return(
            <div className="login-container">
                
                <div>
                    <div className="signup-message">{mesList}</div>
                    <div className="signup-form">
                        <h1>Dream House Online</h1>
                        <span>Create the customer account</span>
                        <div className="login-form-detial">
                            <UserSignUpForm argue={this.showErrMessage}/>
                        </div>
                    </div>
                </div>
            </div>  
        );
    }

}

export default SignUpForm;