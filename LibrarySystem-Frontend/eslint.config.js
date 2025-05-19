import js from "@eslint/js";
import globals from "globals";
import tseslint from "typescript-eslint";
import angular from "@angular-eslint/eslint-plugin";
import angularTemplate from "@angular-eslint/eslint-plugin-template";

export default tseslint.config(
  {
    files: ["**/*.ts"],
    ignores: ["**/*.d.ts", "dist/**", "node_modules/**"]
  },
  // Base JavaScript recommendations
  js.configs.recommended,
  
  // TypeScript configuration
  ...tseslint.configs.recommended,
  
  // Angular-specific configurations
  {
    plugins: {
      "@angular-eslint": angular,
      "@angular-eslint/template": angularTemplate
    },
    rules: {
      ...angular.configs.recommended.rules,
      ...angularTemplate.configs.recommended.rules,
      "@typescript-eslint/explicit-function-return-type": "off",
      "@typescript-eslint/no-unused-vars": ["warn", { "argsIgnorePattern": "^_" }]
    }
  },
  
  // Global browser environment
  {
    languageOptions: {
      globals: {
        ...globals.browser,
        ...globals.node
      }
    }
  },
  
  // Project-specific rules
  {
    rules: {
      "no-console": "warn",
      "eqeqeq": "error",
      "curly": "error",
      "semi": ["error", "always"]
    }
  }
);