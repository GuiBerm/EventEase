'use strict';

class History extends React.Component {

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
                </div>
            );
        }
        else {
            return (
                <div >
                    <button name="home"
                        style={{ backgroundColor: "red"}}
                        onClick={(e) => this.props.onNavigate(e)}>Back</button><br></br>
                    <p style={{ color: "white", border: "solid black", backgroundColor: "#085394", fontSize: "30px" }}>Past Events: </p>
                    <ul>
                        {this.props.eventList.map((element) => (
                            <li style={{ border: '5px solid' }}>
                                {element.name}
                                <button onClick={this.handleAlert}>View Report</button>
                            </li>
                        ))}
                    </ul>
                </div>
            );
        }
    }
}