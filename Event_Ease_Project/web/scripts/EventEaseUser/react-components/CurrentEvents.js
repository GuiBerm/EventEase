'use strict';

class CurrentEvents extends React.Component {

    handleResaleClick = () => {
        alert("Something bad happened :(");
    };

    render() {
        const { ticketList } = this.props;

        if (Array.isArray(ticketList) && ticketList.length > 0) {
            // Render the table if ticketList has elements
            return (
                <div>
                    <button name="home"
                        style={{ backgroundColor: "red" }}
                        onClick={(e) => this.props.onNavigate(e)}>Back</button><br></br>
                    <p style={{ color: "white", border: "solid black", backgroundColor: "#085394", fontSize: "30px" }}>
                        All your Tickets:
                    </p>
                    <table>
                        <tbody>
                            {ticketList.map((element) => (
                                <tr key={element.ticketId} style={{ border: '5px solid' }}>
                                    <td>
                                        {element.info}
                                    </td>
                                    <td>
                                        <button onClick={this.handleResaleClick}>Resale</button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            );
        } else {
            // Render a message if ticketList is empty or not an array
            return (
                <div>
                    <button name="home"
                        style={{ backgroundColor: "red" }}
                        onClick={(e) => this.props.onNavigate(e)}>Back</button><br></br>
                    <p style={{ color: "white", border: "solid black", backgroundColor: "#085394", fontSize: "30px" }}>
                        All your Tickets:
                    </p>
                    <p>No tickets available.</p>
                </div>
            );
        }
    }
}
