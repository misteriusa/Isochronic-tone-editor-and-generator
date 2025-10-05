import { defineConfig } from "vitest/config";
import react from "@vitejs/plugin-react";
import path from "node:path";

export default defineConfig({
  root: path.resolve(__dirname, ".."),
  plugins: [react()],
  test: {
    environment: "jsdom",
    setupFiles: [path.resolve(__dirname, "vitest.setup.ts")],
    globals: true,
    include: ["tests/frontend/**/*.{test,spec}.{ts,tsx}"]
  },
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "."),
      react: path.resolve(__dirname, "node_modules/react"),
      "react-dom": path.resolve(__dirname, "node_modules/react-dom")
    }
  },
  server: {
    fs: {
      allow: [__dirname, path.resolve(__dirname, ".."), path.resolve(__dirname, "../tests")]
    }
  }
});
