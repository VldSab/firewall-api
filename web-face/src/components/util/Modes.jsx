const json = {
    question: 'Do you support cookies in cakes?',
    choices:
    [
      { text: 'Yes', value: '1' },
      { text: 'No', value: '2' }
    ]
  }
  
  const PollOption = ({ options, selected, onChange }) => {
    return (
      <div className="pollOption">
        {options.map((choice, index) => (
          <label key={index}>
            <input type="radio"
              name="vote"
              value={choice.value}
              key={index}
              checked={selected === choice.value}
              onChange={onChange} />
            {choice.text}
          </label>
        ))}
      </div>
    );
  };
  
  class OpinionPoll extends React.Component {
    constructor(props) {
      super(props);
      this.state = { selectedOption: '' }
    }
  
    handleClick() {
      console.log('submitted option', this.state.selectedOption);
    }
  
    handleOnChange(e) {
      console.log('selected option', e.target.value);
      this.setState({ selectedOption: e.target.value});
    }
  
    render() {
      return (
        <div className="poll">
          {this.props.model.question}
          <PollOption
            options={this.props.model.choices}
            onChange={(e) => this.handleOnChange(e)}
            selected={this.state.selectedOption} />
          <button onClick={() => this.handleClick()}>Vote!</button>
        </div>
      );
    }
  }