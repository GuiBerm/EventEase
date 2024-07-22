'use strict';

class ViewEvents extends React.Component {

    handleAlert = () => {
        alert("Something bad happened :(");
    };

    render() {
        if (!(Array.isArray(this.props.eventList) && this.props.eventList.length > 0)) {
            return (
                <div>
                    <button name="home"
                        style={{ backgroundColor: "red"}}
                        onClick={(e) => this.props.onNavigate(e)}>Back</button><br></br>
                    <p style={{ color: "white", border: "solid black", backgroundColor: "#085394", fontSize: "30px" }}>No Events :(</p>
                    <button name= "create" onClick={(e) => this.props.onNavigate(e)} 
                        style={{ backgroundColor: this.props.color, width: "100px", height: "50px", margin: "10px" }}>Create Event</button>
                </div>
            );
        } else {
            return (
                <div>
                    <button name="home"
                        style={{ backgroundColor: "red"}}
                        onClick={(e) => this.props.onNavigate(e)}>Back</button><br></br>
                    <p style={{ color: "white", border: "solid black", backgroundColor: "#085394", fontSize: "30px" }}>All your Events: </p>
                    <table>
                            {this.props.eventList.map((element) => (
                                <tr style={{ border: '5px solid' }} key={element.eventId}>
                                    <td>
                                        {element.name}/
                                        {element.city}/
                                        {element.date}/                                    
                                        {element.numberOfTickets}/
                                        {element.price}
                                    </td>
                                    <td>
                                        <button onClick={this.handleAlert}>Modify</button>
                                        <button onClick={this.handleAlert}>Delete</button>
                                    </td>
                                </tr>
                            ))}
                    </table>
                    <button name= "create" onClick={(e) => this.props.onNavigate(e)} 
                        style={{ backgroundColor: this.props.color, width: "100px", height: "50px", margin: "10px" }}>Create Event</button>                </div>
            );
        }
    }
}
