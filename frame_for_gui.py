import tkinter as tk
from tkinter import scrolledtext

class Dashboard:
    def __init__(self, root):
        self.root = root
        self.root.title("Dashboard")
        
        # Configure main grid
        self.root.grid_columnconfigure(1, weight=1)
        self.root.grid_rowconfigure(0, weight=1)
        
        # Create button panel
        self.create_button_panel()
        
        # Create content frame
        self.content_frame = tk.Frame(self.root)
        self.content_frame.grid(row=0, column=1, sticky="nsew", padx=10, pady=10)
        self.content_frame.grid_columnconfigure(0, weight=1)
        self.content_frame.grid_rowconfigure(0, weight=1)
        
        # Initialize current widgets list
        self.current_widgets = []

    def create_button_panel(self):
        button_panel = tk.Frame(self.root, width=150)
        button_panel.grid(row=0, column=0, sticky="ns", padx=10, pady=10)
        
        # Define your buttons here
        buttons = ['Page1', 'Page2', 'Page3', 'Page4', 'Page5']
        for i, button_name in enumerate(buttons):
            btn = tk.Button(button_panel, text=button_name,
                          command=lambda name=button_name: self.handle_button_click(name))
            btn.pack(fill=tk.X, pady=5)

    def clear_content(self):
        """Clear all widgets from the content frame"""
        for widget in self.current_widgets:
            widget.destroy()
        self.current_widgets = []

    def handle_button_click(self, button_name):
        """Handle button clicks and set up corresponding content"""
        print(f"Button: {button_name} clicked!")
        self.clear_content()
        
        if button_name == "Page1":
            self.setup_dual_textbox_page()
        elif button_name == "Page2":
            self.setup_single_textbox_page()
        elif button_name == "Page3":
            self.setup_single_textbox_with_button()
        elif button_name == "Page4":
            self.setup_single_textbox_with_button()
        elif button_name == "Page5":
            self.setup_readonly_textbox()

    def setup_dual_textbox_page(self):
        """Page with two textboxes and checkboxes"""
        # Output textbox
        output_label = tk.Label(self.content_frame, text="Results:")
        output_label.grid(row=0, column=0, sticky="w", pady=5)
        
        output_text = scrolledtext.ScrolledText(self.content_frame, height=10)
        output_text.grid(row=1, column=0, columnspan=2, sticky="nsew", pady=5)
        
        # Input textbox
        input_label = tk.Label(self.content_frame, text="Enter Data:")
        input_label.grid(row=2, column=0, sticky="w", pady=5)
        
        input_text = scrolledtext.ScrolledText(self.content_frame, height=10)
        input_text.grid(row=3, column=0, columnspan=2, sticky="nsew", pady=5)
        
        # Example checkboxes
        check_var1 = tk.BooleanVar()
        check1 = tk.Checkbutton(self.content_frame, text="Option 1", variable=check_var1)
        check1.grid(row=4, column=0, sticky="w", pady=5)
        
        check_var2 = tk.BooleanVar()
        check2 = tk.Checkbutton(self.content_frame, text="Option 2", variable=check_var2)
        check2.grid(row=5, column=0, sticky="w", pady=5)
        
        # Submit button
        submit_btn = tk.Button(self.content_frame, text="Submit", 
                             command=lambda: print("Submit clicked"))
        submit_btn.grid(row=6, column=0, sticky="w", pady=5)
        
        self.current_widgets.extend([output_label, output_text, input_label, input_text,
                                   check1, check2, submit_btn])

    def setup_single_textbox_page(self):
        """Page with single textbox and processing button"""
        input_text = scrolledtext.ScrolledText(self.content_frame, height=10)
        input_text.grid(row=0, column=0, sticky="nsew", pady=5)
        
        output_text = scrolledtext.ScrolledText(self.content_frame, height=10)
        output_text.grid(row=1, column=0, sticky="nsew", pady=5)
        output_text.config(state='disabled')
        
        process_btn = tk.Button(self.content_frame, text="Process", 
                              command=lambda: print("Process clicked"))
        process_btn.grid(row=2, column=0, sticky="w", pady=5)
        
        self.current_widgets.extend([input_text, output_text, process_btn])

    def setup_single_textbox_with_button(self):
        """Page with single textbox and action button"""
        textbox = scrolledtext.ScrolledText(self.content_frame, height=10)
        textbox.grid(row=0, column=0, sticky="nsew", pady=5)
        
        action_btn = tk.Button(self.content_frame, text="Check", 
                             command=lambda: print("Check clicked"))
        action_btn.grid(row=1, column=0, sticky="w", pady=5)
        
        self.current_widgets.extend([textbox, action_btn])

    def setup_readonly_textbox(self):
        """Page with single readonly textbox"""
        text_widget = scrolledtext.ScrolledText(self.content_frame, height=20)
        text_widget.grid(row=0, column=0, sticky="nsew", pady=5)
        text_widget.insert("1.0", "This is a read-only text area")
        text_widget.config(state='disabled')
        
        self.current_widgets.append(text_widget)

if __name__ == "__main__":
    root = tk.Tk()
    app = Dashboard(root)
    root.geometry("800x600")
    root.mainloop()
