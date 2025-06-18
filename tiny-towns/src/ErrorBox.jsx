import React from 'react';

export default function ErrorBox({ message }) {
  if (!message) return null;

  return (
    <div className="alert alert-danger" role="alert">
      {message}
    </div>
  );
}