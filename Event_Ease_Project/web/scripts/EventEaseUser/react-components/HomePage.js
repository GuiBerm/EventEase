'use strict';

class HomePage extends React.Component {
    render() {
        return (
            <div>
                <div style={{float: "left"}}>
                    <p style={{color: "white", border: "solid black", backgroundColor: "#085394", fontSize: "30px"}}>What do you want to do?</p>
                    <button name="find"
                        style={{ backgroundColor: this.props.color, width: "100px", height: "50px", margin: "10px" }}
                        onClick={(e) => this.props.onNavigate(e)}>Find Events</button>
                    <br/>
                    <button name="current"
                        style={{ backgroundColor: this.props.color, width: "100px", height: "50px", margin: "10px" }}
                        onClick={(e) => this.props.onNavigate(e)}>My Tickets</button>
                </div>
                <div style={{float: "right"}}>
                    <p style={{color: "white", border: "solid black", backgroundColor: "#085394", fontSize: "30px"}}>New Events that may interest you!</p>
                    <EventList eventList= {this.props.eventList}/>
                </div>
            </div>
        );
    }
}
