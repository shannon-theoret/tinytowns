export default function Dropdown({options, onChange, initialValue}) {
    return (
        <select className="form-select" onChange={onChange} value={initialValue}>
            <option value="">Select a building to build</option>
            {options.map((option, index) => (
                <option key={index} value={option.value}>
                    {option.displayName}
                </option>
            ))}
        </select>
    );
}