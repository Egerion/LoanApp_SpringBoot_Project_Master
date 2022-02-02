import React from "react";
import { Navbar, Container, Nav, NavDropdown } from 'react-bootstrap/';

class Menu extends React.Component {

    constructor(props){
        super(props)
    }

    render() {    

        console.log("menu called...");

        return (
            <div className="col-md-4 offset-md-4 ">
            <Navbar bg="light" expand="auto">
                <Container>
                    <Navbar.Brand href="/" style={{color:"blue"}}>Loan App v1.0.0.0 by Ege Demirbaş</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link href="/user/list">User List</Nav.Link>
                            <Nav.Link href="/credit/query">Credit Status</Nav.Link>
                            <Nav.Link href="/credit/register">Apply for New Credit</Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </div>
        );
    }
}

export default Menu;