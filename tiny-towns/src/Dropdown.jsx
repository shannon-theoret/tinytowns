export default function Dropdown({options, onChange, initialValue}) {
    return (
        <select onChange={onChange} value={initialValue}>
            <option value="">Select an option</option>
            {options.map((option, index) => (
                <option key={index} value={option.value}>
                    {option.displayName}
                </option>
            ))}
        </select>
    );
}