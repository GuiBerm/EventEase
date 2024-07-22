'use strict';

class HomePage extends React.Component {
    render() {
        return (
            <div>
                <p style={{ color: "white", border: "solid black", backgroundColor: "#085394", fontSize: "30px" }}>What do you want to do?</p>
                <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                    <button name="history"
                        style={{ backgroundColor: this.props.color, width: "200px", height: "100px", margin: "10px" }}
                        onClick={(e) => this.props.onNavigate(e)}>View Historic Events (and their reports)</button>
                    <button name="view"
                        style={{ backgroundColor: this.props.color, width: "200px", height: "100px", margin: "10px" }}
                        onClick={(e) => this.props.onNavigate(e)}>Modify Your Events</button>
                </div>
            </div>
        );
    }
}
