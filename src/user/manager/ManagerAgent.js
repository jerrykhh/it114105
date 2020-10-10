import React, { Component } from 'react';
import agentJson from '../../server/agent.json';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Button from '@material-ui/core/Button';
import MaterialTable from 'material-table';
import tableIcons from './TableIcon';



const createData = (id, username, email, enable, name, tel) => {
    return { id, username, email, enable, name, tel };
}

const rows = [];

agentJson.forEach((obj, index) => {
    rows.push(createData(obj.id, obj.username, obj.email, obj.enable, obj.firstName + " " + obj.lastName, obj.tel));
})


class ManagerAgent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            status: -1,
            open: false,
            rows,
            username: "",
            firstName: "",
            lastName: "",
            email: "",
            password: "",
            tel: "",
            cPassword: "",
            errorCreMes: [],
            errorPwd: false,
            errorEmail: false
        }
        console.log(this.state);
    }

    deleteRowData = (rows) => {
        this.setState({ rows });
    }

    changeEnableType = (rows, status) => {
        this.setState(rows);
        (status) ? this.setState({ status: 0 }) : this.setState({ status: 1 });
    }

    handleClickOpenCreateAccountBox = () => {
        this.setState({ open: true })
    }

    handleClickCloseCreateAccountBox = () => {
        this.setState({ open: false })
    }

    initStateFormValue = () => {
        this.setState({
            errorCreMes: [],
            errorPwd: false,
            password: "",
            cPassword: ""
        })
    }

    handleCreateAccount = () =>{
        this.initStateFormValue();
        if(this.state.cPassword !== this.state.password){
            this.setState({errorCreMes: ["Password and confirm password is not match"], errorPwd: true});
            return;
        }

        const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if (!re.test(String(this.state.email).toLowerCase())) {
            this.setState({errorCreMes: ["Email format is incorrect"], errorPwd: true, errorEmail: true});
            return
        }
        var isVaild = true;
        var message = "Missing the ";
        if(this.state.username === ""){
            message += "username, ";
            isVaild = false;
        }

        if(this.state.firstName === ""){
            message += "first name, ";
            isVaild = false;
        }

        if(this.state.lastName === ""){
            message += "last name, ";
            isVaild = false;
        }
        if(this.state.email === ""){
            message += "email, ";
            isVaild = false;
        }

        if(this.state.password === ""){
            message += "password, ";
            isVaild = false;
            this.setState({errorPwd: true});
        }
        if(this.state.cPassword === ""){
            message += "confirm password, ";
            isVaild = false;
            this.setState({errorPwd: true});
        } 

        if(this.state.tel === ""){
            message += "tel, ";
            isVaild = false;
        }

        if(isVaild){
            var rows = this.state.rows;
            const newAccount = {
                id: this.state.rows.length + 1,
                username: this.state.username,
                email: this.state.email,
                enable: false,
                name: this.state.firstName + " " +this.state.lastName,
                tel: this.state.tel
            }
            rows.push(newAccount);
            this.setState({rows});
        }else{
            this.setState({errorCreMes: [message.slice(0, -1)]});
        }

        
       

    }

    handleChange = (event) => {
        let changeName = event.target.name;
        this.setState({[changeName]: event.target.value});
    }

    render = () => {
        return (
            <div>
                {
                    (this.state.status == 0) ?
                        <div className="bs-callout bs-callout-danger" style={{ marginBottom: "30px" }}><h4>Update Successful </h4>User is Disabled</div> :
                        (this.state.status == 1) ?
                            <div className="bs-callout bs-callout-success" style={{ marginBottom: "30px" }}><h4>Update Successful</h4>User is Enabled</div> : ""
                }

                <MaterialTable
                    icons={tableIcons}
                    title="Agents"
                    key={this.state.rows.length}
                    columns={[
                        { title: '# id', field: 'id', type: 'string' },
                        { title: 'Username', field: 'username', type: 'string' },
                        { title: 'Email', field: 'email', type: 'string' },
                        { title: 'Enable', field: 'enable', type: 'boolean' },
                        { title: 'Name', field: 'name', type: 'string' },
                        { title: 'Tel', field: 'tel', type: 'string' }
                    ]}
                    data={this.state.rows}
                    options={{
                        pageSize: 10,
                        pageSizeOptions: 10,
                        actionsColumnIndex: -1
                    }}
                    actions={[
                        rowData => ({
                            icon: (rowData.enable) ? tableIcons.SwapHorizTrue : tableIcons.SwapHorizFalse
                            ,
                            tooltip: (rowData.enable) ? 'Enable User' : 'Disable User',
                            onClick: (event, rowData) => {
                                this.state.rows.forEach((obj, index) => {
                                    if (obj.id == rowData.id) {
                                        var newRows = this.state.rows;
                                        let enableStatus = newRows[index].enable;
                                        newRows[index].enable = !newRows[index].enable;
                                        this.changeEnableType(newRows, enableStatus);
                                    }
                                });
                            }
                        }),
                        {
                            icon: tableIcons.AddCircle,
                            tooltip: 'Add User',
                            isFreeAction: true,
                            onClick: (event) => this.handleClickOpenCreateAccountBox()
                        }
                    ]}
                />
                <Dialog open={this.state.open} onClose={this.handleClickCloseCreateAccountBox} aria-labelledby="form-dialog-title">
                    <DialogTitle id="form-dialog-title">Create Agent Account</DialogTitle>
                    <DialogContent>
                        <div className="error-message">
                            {
                            (this.state.errorCreMes.length > 0)?
                                <div className="bs-callout bs-callout-danger" style={{marginBottom:50}} ><h4>Create Faild</h4>{this.state.errorCreMes[0]}</div>
                            : ""
                            }
                        </div>
                        <DialogContentText>
                            To create the agent account, please enter the information
                        </DialogContentText>
                        <TextField
                        error={(this.state.errorCreMes.length > 0 && this.state.username === "")? "true": ""}
                            name="username"
                            label="Username"
                            type="text"
                            variant="filled"
                            value={this.state.username}
                            onChange={this.handleChange}
                            fullWidth/>
                        <TextField
                        error={(this.state.errorCreMes.length > 0 && this.state.firstName === "")? "true": ""}
                            margin="dense"
                            name="firstName"
                            label="First Name"
                            type="text"
                            variant="filled"
                            value={this.state.firstName}
                            onChange={this.handleChange}
                            />
                        <TextField
                            error={(this.state.errorCreMes.length > 0 && this.state.lastName === "")? "true": ""}
                            margin="dense"
                            name="lastName"
                            label="Last Name"
                            type="text"
                            variant="filled"
                            style={{marginLeft: 20}}
                            value={this.state.lastName}
                            onChange={this.handleChange}
                            />
                        <TextField
                        error={(this.state.errorCreMes.length > 0 && this.state.email === "" || this.state.errorEmail)? "true": ""}
                            margin="dense"
                            name="email"
                            label="Email Address"
                            type="email"
                            variant="filled"
                            value={this.state.email}
                            onChange={this.handleChange}
                            fullWidth/>
                        <TextField
                        error={(this.state.errorCreMes.length > 0 && this.state.tel === "")? "true": ""}
                            margin="dense"
                            name="tel"
                            label="Telphone"
                            type="tel"
                            variant="filled"
                            value={this.state.tel}
                            onChange={this.handleChange}
                            style={{width: '60%'}}/>
                        <TextField
                            error={(this.state.errorPwd)? "true": ""}
                            margin="dense"
                            name="password"
                            label="Password"
                            type="password"
                            variant="filled"
                            value={this.state.password}
                            onChange={this.handleChange}
                            fullWidth/>
                        <TextField
                            error={(this.state.errorPwd)? "true": ""}
                            margin="dense"
                            name="cPassword"
                            label="Confirm Password"
                            type="password"
                            variant="filled"
                            value={this.state.cPassword}
                            onChange={this.handleChange}
                            fullWidth/>
                        
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={this.handleClickCloseCreateAccountBox} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={this.handleCreateAccount} color="primary">
                            Create
                        </Button>
                    </DialogActions>
                </Dialog>
            </div>
        )

    }


}

export default ManagerAgent;