import React, { useState } from "react";
import axios from "axios";
import "./App.css";

const API_BASE = "http://localhost:8080";

export default function App() {
  const [url, setUrl] = useState("");
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  async function handleCrawl() {
    if (!url.trim()) return;
    setLoading(true);
    setError("");
    setResult(null);

    // ✅ Automatically add https:// if missing
    const fullUrl = url.startsWith("http") ? url : `https://${url}`;

    try {
      const res = await axios.post(`${API_BASE}/api/crawl`, { url: fullUrl });
      setResult(res.data);
    } catch (err) {
      console.error(err);
      setError("⚠️ Failed to fetch. Check the URL or backend connection.");
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="app-container">
      <header className="header glass-card">
        <h1 className="title">🌐 Smart Web Crawler</h1>
        <p className="subtitle">
          Crawl any website and extract all links & metadata instantly.
        </p>
      </header>

      <div className="crawler-box glass-card">
        <div className="input-row">
          <input
            type="text"
            value={url}
            onChange={(e) => setUrl(e.target.value)}
            placeholder="Enter a website (e.g., www.example.com)"
            className="url-input"
          />
          <button onClick={handleCrawl} disabled={loading} className="crawl-btn">
            {loading ? "Crawling..." : "Start"}
          </button>
        </div>

        {error && <p className="error">{error}</p>}

        {loading && (
          <div className="loading-section">
            <div className="spinner"></div>
            <p>Crawling website... Please wait</p>
          </div>
        )}

        {result && (
          <div className="result-card">
            <div className="result-header">
              <h2>{result.title || "Untitled Page"}</h2>
              <p className="url-display">{result.url}</p>
              <p className="description">
                {result.description || "No meta description found."}
              </p>
              <p className="timestamp">
                🕒 Crawled at: {new Date(result.timestamp).toLocaleString("en-IN")}
              </p>
            </div>

            <div className="links-section">
              <h3>🔗 Found Links ({result.totalLinks})</h3>
              <ul className="links-list">
                {result.links.map((link, i) => (
                  <li key={i} className="link-item">
                    <a href={link.href} target="_blank" rel="noopener noreferrer">
                      {link.text || link.href}
                    </a>
                  </li>
                ))}
              </ul>
            </div>
          </div>
        )}
      </div>

      <footer className="footer glass-card">
        <p>⚙️ Built by Gautham’s Web Crawler App © 2025</p>
      </footer>
    </div>
  );
}
