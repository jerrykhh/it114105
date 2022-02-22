import React, { Component } from 'react';
import Button from '@material-ui/core/Button';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import { Link } from 'react-router-dom';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import { withStyles } from '@material-ui/core/styles';
import userJson from '../../server/user.json';
import appoJson from '../../server/appointment.json';


const StyledTableCell = withStyles((theme) => ({
    head: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
    },
    body: {
        fontSize: 14,
    },
}))(TableCell);

class ManagerCustomerDetail extends Component {

    constructor(props) {
        super(props);
        this.state = {
            user: {
                appointments: []
            }
        };
    }

    componentDidMount = () => {
        userJson.forEach((userObj, index) => {
            if (userObj.id == this.props.match.params.id) {
                this.setState({ user: userObj });
                return;
            }
        });
    }

    getStateAppoint = () => {
    }


    render = () => {

        console.log();
        return (
            <div>
                <Link to='../customer'>
                    <Button
                        variant="contained"
                        color="primary"
                        size="small"
                        startIcon={<ArrowBackIosIcon />}>
                        Back
                    </Button>
                </Link>
                <Paper variant="outlined" square style={{ marginTop: 20, padding: 50, paddingLeft: 90 }}>
                    <div>
                        <h1 style={{ textAlign: "left" }}>User Information</h1>
                        <br />
                        <p className="user-inf-details">
                            Id: {this.state.user.id}
                            <br />
                           Username: {this.state.user.username}
                            <br />
                           Email: {this.state.user.email}
                            <br />
                           Tel: {this.state.user.tel}
                            <br />
                           Full Name: {this.state.user.firstName + " " + this.state.user.lastName}
                            <br />
                        </p>
                    </div>
                    <TableContainer style={{ marginTop: 30 }}>
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <StyledTableCell>uid</StyledTableCell>
                                    <StyledTableCell>date</StyledTableCell>
                                    <StyledTableCell>status</StyledTableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {appoJson.map((obj, index) => (

                                    (this.state.user.appointments).map(item => (
                                        (obj.uid === item) ?
                                            <TableRow>
                                                <TableCell>{item}</TableCell>
                                                <TableCell>{obj.date}</TableCell>
                                                <TableCell>{obj.status}</TableCell>
                                            </TableRow> : ""
                                    ))
                                ))}

                            </TableBody>
                        </Table>
                    </TableContainer>

                </Paper>
            </div>
        );
    }
}

export default ManagerCustomerDetail;